package com.locatech.api.services;

import com.locatech.api.domain.Aluguel;
import com.locatech.api.domain.Pessoa;
import com.locatech.api.domain.Veiculo;
import com.locatech.api.exceptions.RegraNegocioException;
import com.locatech.api.repositories.AluguelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final PessoaService pessoaService;
    private final VeiculoService veiculoService;

    // Injetamos os Services e não os Repositories diretamente para reaproveitar regras!
    public AluguelService(AluguelRepository aluguelRepository, PessoaService pessoaService, VeiculoService veiculoService) {
        this.aluguelRepository = aluguelRepository;
        this.pessoaService = pessoaService;
        this.veiculoService = veiculoService;
    }

    @Transactional // Garante que, se der erro no meio, ele desfaz tudo no banco de dados
    public Aluguel realizarAluguel(Aluguel aluguel) {
        // 1. Busca as entidades completas no banco para garantir que existem
        Pessoa pessoa = pessoaService.buscarPorId(aluguel.getPessoa().getId());
        Veiculo veiculo = veiculoService.buscarPorId(aluguel.getVeiculo().getId());

        // 2. Regra de Negócio: Verificação de disponibilidade do Veículo
        if (!veiculo.getDisponivel()) {
            throw new RegraNegocioException("O veículo selecionado não está disponível para locação no momento.");
        }

        // 3. Validação de datas
        if (aluguel.getDataDevolucao().isBefore(aluguel.getDataInicio())) {
            throw new RegraNegocioException("A data de devolução não pode ser anterior à data de início.");
        }

        // 4. Associa as entidades completas ao aluguel
        aluguel.setPessoa(pessoa);
        aluguel.setVeiculo(veiculo);

        // 5. Atualiza o status do veículo para "Indisponível" (false)
        veiculoService.atualizarStatusDisponibilidade(veiculo, false);

        // 6. Salva e retorna o aluguel
        return aluguelRepository.save(aluguel);
    }
}
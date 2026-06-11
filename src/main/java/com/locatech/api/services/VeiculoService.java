package com.locatech.api.services;

import com.locatech.api.domain.Veiculo;
import com.locatech.api.exceptions.RecursoNaoEncontradoException;
import com.locatech.api.exceptions.RegraNegocioException;
import com.locatech.api.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo salvar(Veiculo veiculo) {
        if (repository.existsByPlaca(veiculo.getPlaca())) {
            throw new RegraNegocioException("Já existe um veículo cadastrado com esta placa.");
        }
        return repository.save(veiculo);
    }

    public Veiculo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado com o ID: " + id));
    }

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    // Método para atualizar o status do veículo internamente
    public void atualizarStatusDisponibilidade(Veiculo veiculo, boolean disponivel) {
        veiculo.setDisponivel(disponivel);
        repository.save(veiculo);
    }
}
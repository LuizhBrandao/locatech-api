package com.locatech.api.services;

import com.locatech.api.domain.Pessoa;
import com.locatech.api.exceptions.RecursoNaoEncontradoException;
import com.locatech.api.exceptions.RegraNegocioException;
import com.locatech.api.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    // Injeção de dependência via construtor (Boa prática!)
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        if (repository.existsByCpf(pessoa.getCpf())) {
            throw new RegraNegocioException("Já existe uma pessoa cadastrada com este CPF.");
        }
        if (repository.existsByEmail(pessoa.getEmail())) {
            throw new RegraNegocioException("Já existe uma pessoa cadastrada com este E-mail.");
        }
        return repository.save(pessoa);
    }

    public Pessoa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada com o ID: " + id));
    }

    public List<Pessoa> listarTodas() {
        return repository.findAll();
    }
}
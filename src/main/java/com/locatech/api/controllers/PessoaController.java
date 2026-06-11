package com.locatech.api.controllers;

import com.locatech.api.domain.Pessoa;
import com.locatech.api.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas") // [cite: 13, 31]
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes (Pessoas)")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping // [cite: 9]
    @Operation(summary = "Cadastra um novo cliente", description = "Insere um cliente no sistema com validação de CPF e E-mail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"), // [cite: 15]
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos ou duplicados") // [cite: 16]
    })
    public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = service.salvar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa); // [cite: 15]
    }

    @GetMapping // [cite: 8]
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista contendo todos os clientes cadastrados.")
    public ResponseEntity<List<Pessoa>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas()); // [cite: 15]
    }

    @GetMapping("/{id}") // [cite: 8]
    @Operation(summary = "Busca um cliente por ID", description = "Retorna os dados detalhados de um cliente específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"), // [cite: 15]
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado") // [cite: 16]
    })
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
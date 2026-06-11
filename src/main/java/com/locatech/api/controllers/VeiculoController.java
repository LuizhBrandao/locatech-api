package com.locatech.api.controllers;

import com.locatech.api.domain.Veiculo;
import com.locatech.api.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/veiculos") // [cite: 13, 31]
@Tag(name = "Veículos", description = "Endpoints para gerenciamento da frota de veículos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping // [cite: 9]
    @Operation(summary = "Cadastra um novo veículo", description = "Insere um veículo na frota. A disponibilidade inicial é definida como ativa.")
    public ResponseEntity<Veiculo> salvar(@Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(veiculo));
    }

    @GetMapping // [cite: 8]
    @Operation(summary = "Lista todos os veículos", description = "Retorna toda a frota cadastrada.")
    public ResponseEntity<List<Veiculo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
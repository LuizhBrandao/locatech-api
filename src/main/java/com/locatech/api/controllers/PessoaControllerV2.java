package com.locatech.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v2/pessoas") // [cite: 31, 33]
@Tag(name = "Clientes - Versão 2", description = "Demonstração de coexistência de múltiplas versões da API")
public class PessoaControllerV2 {

    @GetMapping
    @Operation(summary = "Lista clientes na V2 (Simulação)", description = "Retorna dados com uma estrutura ou metadados atualizados para novos clientes da API.")
    public ResponseEntity<Map<String, Object>> listarTodasV2() {
        // Simulação de uma resposta modificada estruturalmente na versão 2
        return ResponseEntity.ok(Map.of(
                "versao da api", "v2",
                "nota", "Esta rota coexiste com a v1 demonstrando retrocompatibilidade"
        ));
    }
}
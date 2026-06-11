package com.locatech.api.controllers;

import com.locatech.api.domain.Aluguel;
import com.locatech.api.services.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alugueis") // [cite: 13, 31]
@Tag(name = "Aluguéis", description = "Endpoints para gerenciamento do ciclo de locações")
public class AluguelController {

    private final AluguelService service;

    public AluguelController(AluguelService service) {
        this.service = service;
    }

    @PostMapping // [cite: 9]
    @Operation(summary = "Realiza uma nova locação", description = "Valida o cliente, o veículo, checa a disponibilidade do carro e agenda as datas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "21", description = "Locação efetuada e veículo reservado"),
            @ApiResponse(responseCode = "400", description = "Veículo indisponível ou inconsistência nas datas informadas")
    })
    public ResponseEntity<Aluguel> realizarAluguel(@Valid @RequestBody Aluguel aluguel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.realizarAluguel(aluguel));
    }
}
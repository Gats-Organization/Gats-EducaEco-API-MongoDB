package com.example.springarearestrita.controllers;

import com.example.springarearestrita.models.Resultado;
import com.example.springarearestrita.services.ResultadosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Resultados", description = "APIs para gerenciamento de resultados de formulários")
@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadosService resultadosService;

    @Operation(summary = "Adicionar um novo resultado", description = "Cria um novo resultado com nome, email e o resultado do formulário")
    @PostMapping("/adicionar")
    public Resultado adicionarResultado(
            @Parameter(description = "Nome do participante", example = "João Silva") @RequestParam String nome,
            @Parameter(description = "Email do participante", example = "joao.silva@example.com") @RequestParam String email,
            @Parameter(description = "Resultado do formulário", example = "Estundantil") @RequestParam String resultado) {
        return resultadosService.adicionarResultado(nome, email, resultado);
    }
}

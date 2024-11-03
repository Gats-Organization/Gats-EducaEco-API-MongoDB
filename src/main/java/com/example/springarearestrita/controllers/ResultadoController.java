package com.example.springarearestrita.controllers;

import com.example.springarearestrita.models.Resultado;
import com.example.springarearestrita.services.ResultadosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Resultados", description = "APIs para gerenciamento de resultados de formulários")
@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadosService resultadosService;

    @Operation(summary = "Listar resultados com filtros opcionais", description = "Retorna uma lista de resultados filtrados por resultado e data")
    @GetMapping("/listar")
    public List<Resultado> listarResultados(
            @Parameter(description = "Filtrar por tipo de resultado: 'Estudantil' ou 'Não tenho motivação'", required = false)
            @RequestParam(required = false) String filtroResultado,

            @Parameter(description = "Filtrar por período: 'Hoje', 'Essa Semana' ou 'Esse mês'", required = false)
            @RequestParam(required = false) String filtroData) {

        return resultadosService.listarResultadosFiltrados(filtroResultado, filtroData);
    }

    @Operation(summary = "Adicionar um novo resultado", description = "Cria um novo resultado com nome, email e o resultado do formulário")
    @PostMapping("/adicionar")
    public Resultado adicionarResultado(
            @Parameter(description = "Dados do participante", required = true) @RequestBody Resultado resultado) {
        return resultadosService.adicionarResultado(resultado.getNome(), resultado.getEmail(), resultado.getResultado());
    }
}

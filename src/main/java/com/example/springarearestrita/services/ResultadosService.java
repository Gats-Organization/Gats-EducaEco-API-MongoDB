package com.example.springarearestrita.services;

import com.example.springarearestrita.models.Resultado;
import com.example.springarearestrita.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class ResultadosService {
    private final ResultadoRepository resultadoRepository;

    @Autowired
    public ResultadosService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public Resultado adicionarResultado(String nome, String email, String resultado) {
        ZonedDateTime dataHoraBrasilia = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        Date dataHora = Date.from(dataHoraBrasilia.toInstant());
        Resultado novoResultado = new Resultado(nome, email, dataHora, resultado);
        return resultadoRepository.save(novoResultado);
    }
}

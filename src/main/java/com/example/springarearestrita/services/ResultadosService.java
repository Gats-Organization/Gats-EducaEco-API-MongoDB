package com.example.springarearestrita.services;

import com.example.springarearestrita.models.Resultado;
import com.example.springarearestrita.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultadosService {
    private final ResultadoRepository resultadoRepository;

    @Autowired
    public ResultadosService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public Resultado adicionarResultado(String nome, String email, String resultado) {
        ZonedDateTime dataHoraBrasilia = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        Resultado novoResultado = new Resultado(nome, email, dataHoraBrasilia.toLocalDateTime(), resultado);
        return resultadoRepository.save(novoResultado);
    }

    public List<Resultado> listarResultadosFiltrados(String filtroResultado, String filtroData) {
        List<Resultado> resultados = resultadoRepository.findAll();

        // Filtro por tipo de resultado
        if (filtroResultado != null) {
            resultados = resultados.stream()
                    .filter(r -> r.getResultado().equalsIgnoreCase(filtroResultado))
                    .collect(Collectors.toList());
        }

        if (filtroData != null) {
            LocalDateTime startDateTime = null;
            LocalDateTime endDateTime = LocalDateTime.now();

            switch (filtroData.toLowerCase()) {
                case "hoje":
                    startDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
                    break;
                case "essa semana":
                    startDateTime = LocalDate.now().with(java.time.DayOfWeek.MONDAY).atStartOfDay();
                    break;
                case "esse mês":
                    startDateTime = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
                    break;
            }

            if (startDateTime != null) {
                Instant startInstant = startDateTime.atZone(ZoneId.systemDefault()).toInstant();
                Instant endInstant = endDateTime.atZone(ZoneId.systemDefault()).toInstant();

                resultados = resultados.stream()
                        .filter(r -> {
                            Instant dataRegistroInstant = r.getDataRegistro().atZone(ZoneId.systemDefault()).toInstant();
                            return dataRegistroInstant.isAfter(startInstant) && dataRegistroInstant.isBefore(endInstant);
                        })
                        .collect(Collectors.toList());
            }
        }

        return resultados;
    }
}

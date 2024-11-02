package com.example.springarearestrita.repository;

import com.example.springarearestrita.models.Resultado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultadoRepository extends MongoRepository<Resultado, String> {

}


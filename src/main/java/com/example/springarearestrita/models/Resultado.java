package com.example.springarearestrita.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Document(collection = "resultados")
@Schema(description = "Modelo que representa o resultado do formulário.")
public class Resultado {

    @Id
    @Schema(description = "ID único do resultado", example = "507f1f77bcf86cd799439011")
    private String id;

    @Schema(description = "Nome do participante", example = "João Silva")
    private String nome;

    @Schema(description = "Email do participante", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Data de registro do resultado", example = "2024-11-02T10:15:30")
    private Date dataRegistro;

    @Schema(description = "Resultado do formulário", example = "Estudantil")
    private String resultado;

    // Construtores, Getters e Setters
    public Resultado() {}

    public Resultado(String nome, String email, Date dataRegistro, String resultado) {
        this.nome = nome;
        this.email = email;
        this.dataRegistro = dataRegistro;
        this.resultado = resultado;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getDataRegistro() {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(this.dataRegistro.toInstant(), ZoneId.of("America/Sao_Paulo"));
        return zonedDateTime.toString();
    }
    public String getResultado() { return resultado; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setDataRegistro(Date dataRegistro) { this.dataRegistro = dataRegistro; }
    public void setResultado(String resultado) { this.resultado = resultado; }
}

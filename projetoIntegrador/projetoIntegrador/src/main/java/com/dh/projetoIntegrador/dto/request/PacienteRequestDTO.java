package com.dh.projetoIntegrador.dto.request;

import com.dh.projetoIntegrador.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteRequestDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private Endereco endereco;
    private String rg;
    private LocalDate data;


    public PacienteRequestDTO(String nome, String sobrenome, Endereco endereco, String rg, LocalDate data) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.rg = rg;
        this.data = data;
    }
}
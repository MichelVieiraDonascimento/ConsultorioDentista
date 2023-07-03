package com.dh.projetoIntegrador.dto.response;

import com.dh.projetoIntegrador.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteResponseDTO {


    private String nome;
    private String sobrenome;
    private Endereco endereco;
    private String rg;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;


}

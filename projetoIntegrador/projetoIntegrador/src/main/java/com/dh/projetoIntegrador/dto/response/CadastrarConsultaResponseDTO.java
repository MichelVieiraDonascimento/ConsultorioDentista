package com.dh.projetoIntegrador.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CadastrarConsultaResponseDTO {
    private String rg;
    private String matricula;
    private String dataHora;
}

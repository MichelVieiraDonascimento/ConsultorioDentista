package com.dh.projetoIntegrador.dto.response;

import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaResponseDTO {
    private Paciente paciente;
    private Dentista dentista;
    private LocalDateTime dataHora;



}

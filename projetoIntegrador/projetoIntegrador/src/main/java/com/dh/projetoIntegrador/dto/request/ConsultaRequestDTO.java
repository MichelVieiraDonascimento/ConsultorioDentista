package com.dh.projetoIntegrador.dto.request;

import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaRequestDTO {
    private Integer id;
    private Paciente paciente;
    private Dentista dentista;
    private LocalDateTime dataHora;

    public ConsultaRequestDTO(Paciente paciente, Dentista dentista, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.dentista = dentista;
        this.dataHora = dataHora;
    }
}

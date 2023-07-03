package com.dh.projetoIntegrador.model;

import com.dh.projetoIntegrador.dto.response.CadastrarConsultaResponseDTO;
import com.dh.projetoIntegrador.dto.response.ConsultaResponseDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "TB_CONSULTA")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_dentista")
    private Dentista dentista;

    @Column(name = "DataHora" , unique = true)
    private LocalDateTime  dataHora;

    public Consulta(ConsultaResponseDTO consultaDTO) {
        this.dentista = consultaDTO.getDentista();
        this.paciente = consultaDTO.getPaciente();

        this.dataHora = LocalDateTime.parse(consultaDTO.getDataHora().toString());
    }


}

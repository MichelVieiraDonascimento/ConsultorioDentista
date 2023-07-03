package com.dh.projetoIntegrador.model;

import com.dh.projetoIntegrador.dto.response.DentistaResponseDTO;
import com.dh.projetoIntegrador.dto.response.PacienteResponseDTO;
import com.dh.projetoIntegrador.repository.PacienteRepository;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "TB_PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;


    @Column(name = "rg", unique = true)
    private String rg;

    @Column(name = "data")
    private LocalDate data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Paciente(PacienteResponseDTO pacienteResponseDTO) {
        this.nome = pacienteResponseDTO.getNome();
        this.sobrenome = pacienteResponseDTO.getSobrenome();
        this.rg = pacienteResponseDTO.getRg();
        this.data = pacienteResponseDTO.getData();
        this.endereco = pacienteResponseDTO.getEndereco();
    }



    public Paciente(String nome, String sobrenome, String rg, String data, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.data = LocalDate.parse(data);
        this.endereco = endereco;
    }
}

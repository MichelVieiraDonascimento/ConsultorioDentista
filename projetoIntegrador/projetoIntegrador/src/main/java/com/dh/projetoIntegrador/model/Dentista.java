package com.dh.projetoIntegrador.model;


import com.dh.projetoIntegrador.dto.response.DentistaResponseDTO;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "TB_DENTISTA")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "matricula", unique = true)
    private String matricula;

    public Dentista(DentistaResponseDTO dentistaResponseDTO) {
        this.nome = dentistaResponseDTO.getNome();
        this.sobrenome = dentistaResponseDTO.getSobrenome();
        this.matricula = dentistaResponseDTO.getMatricula();
    }



}

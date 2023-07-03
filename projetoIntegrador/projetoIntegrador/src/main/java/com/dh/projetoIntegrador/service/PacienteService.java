package com.dh.projetoIntegrador.service;

import com.dh.projetoIntegrador.dto.request.PacienteRequestDTO;
import com.dh.projetoIntegrador.dto.response.PacienteResponseDTO;
import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.model.Paciente;
import com.dh.projetoIntegrador.repository.DentistaRepository;
import com.dh.projetoIntegrador.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public String salvar(Paciente paciente) {
        Paciente pascienteSalva = pacienteRepository.save(paciente);
        return "OK";
    }


    private PacienteResponseDTO pacienteResponseDTO(Paciente paciente) {
        return PacienteResponseDTO.builder()
                .nome(paciente.getNome())
                .sobrenome(paciente.getSobrenome())
                .endereco(paciente.getEndereco())
                .rg(paciente.getRg())
                .data(paciente.getData())
                .build();
    }

    private Paciente toPaciente(PacienteRequestDTO requestDTO) {
        return Paciente.builder()
                .nome(requestDTO.getNome())
                .sobrenome(requestDTO.getSobrenome())
                .endereco(requestDTO.getEndereco())
                .rg(requestDTO.getRg())
                .data(requestDTO.getData())
                .build();
    }
//
    public PacienteResponseDTO modificar(Integer id, PacienteRequestDTO requestDTO ) throws SQLException{
        Paciente entity = toPaciente(requestDTO);
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        validaAtributo(entity, pacienteBuscado);

        Paciente paciente = pacienteRepository.save(pacienteBuscado);
        PacienteResponseDTO pacienteResponseDTO = pacienteResponseDTO(paciente);
        return pacienteResponseDTO;
    }


    private  static  void validaAtributo(Paciente entity, Paciente pacienteBuscado) {
        if (entity.getNome() != null) {
            pacienteBuscado.setNome(entity.getNome());
        }

        if (entity.getSobrenome() != null) {
            pacienteBuscado.setSobrenome(entity.getSobrenome());
        }

        if (entity.getRg() != null) {
            pacienteBuscado.setRg(entity.getRg());
        }

        if (entity.getData() != null) {
            pacienteBuscado.setData(entity.getData());
        }

        if (entity.getEndereco() != null) {
            pacienteBuscado.setEndereco(entity.getEndereco());
        }
    }

    public void excluir(Integer id){
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> buscarTodos(){return pacienteRepository.findAll(); }

    public Optional<Paciente> buscarId(Integer id) { return pacienteRepository.findById(id); }

    public Optional<Paciente> buscarPorNome(String nome) {
        return pacienteRepository.findJogadorByNomeContainingIgnoreCase(nome);
    }

    public Optional<Paciente> buscarPorRg(String rg) {
        return pacienteRepository.findJogadorByRgContainingIgnoreCase(rg);
    }

}

package com.dh.projetoIntegrador.service;


import com.dh.projetoIntegrador.dto.request.DentistaRequestDTO;
import com.dh.projetoIntegrador.dto.request.PacienteRequestDTO;
import com.dh.projetoIntegrador.dto.response.DentistaResponseDTO;
import com.dh.projetoIntegrador.dto.response.PacienteResponseDTO;
import com.dh.projetoIntegrador.model.Paciente;
import com.dh.projetoIntegrador.repository.DentistaRepository;
import com.dh.projetoIntegrador.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService{

    @Autowired
    private DentistaRepository dentistaRepository;


    public String salvar(Dentista dentista) {
        Dentista dentistaSalva = dentistaRepository.save(dentista);
        return "OK";
    }


    private DentistaResponseDTO dentistaResponseDTO(Dentista dentista) {
        return DentistaResponseDTO.builder()
                .nome(dentista.getNome())
                .sobrenome(dentista.getSobrenome())
                .matricula(dentista.getMatricula())
                .build();
    }

    private Dentista toDentista(DentistaRequestDTO requestDTO) {
        return Dentista.builder()
                .nome(requestDTO.getNome())
                .sobrenome(requestDTO.getSobrenome())
                .matricula(requestDTO.getMatricula())
                .build();
    }


    public DentistaResponseDTO modificar(Integer id, DentistaRequestDTO requestDTO ) throws SQLException {
        Dentista entity = toDentista(requestDTO);
        Dentista dentistaBuscado = dentistaRepository.findById(id).orElse(null);

        validaAtributo(entity, dentistaBuscado);

        Dentista dentista = dentistaRepository.save(dentistaBuscado);
        DentistaResponseDTO dentistaResponseDTO = dentistaResponseDTO(dentista);
        return dentistaResponseDTO;
    }


    private  static  void validaAtributo(Dentista entity, Dentista dentistaBuscado) {
        if (entity.getNome() != null) {
            dentistaBuscado.setNome(entity.getNome());
        }

        if (entity.getSobrenome() != null) {
            dentistaBuscado.setSobrenome(entity.getSobrenome());
        }

        if (entity.getMatricula()!= null) {
            dentistaBuscado.setMatricula(entity.getMatricula());
        }

    }

    public void excluir(Integer id) {
        dentistaRepository.deleteById(id);


    }

    public List<Dentista> buscarTodos() {
        return dentistaRepository.findAll();
    }

    public Optional<Dentista> buscarId(Integer id){
        return dentistaRepository.findById(id);
    }

    public Optional<Dentista> buscarPorMatricula(String matricula) {
        return dentistaRepository.findJogadorByMatricula(matricula);
    }

}

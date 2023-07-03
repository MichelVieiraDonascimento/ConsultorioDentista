package com.dh.projetoIntegrador.service;

import com.dh.projetoIntegrador.dto.request.ConsultaRequestDTO;
import com.dh.projetoIntegrador.dto.response.ConsultaResponseDTO;
import com.dh.projetoIntegrador.model.Consulta;
import com.dh.projetoIntegrador.repository.ConsulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsulaRepository consulaRepository;

    public String salvar(Consulta consulta) {
        Consulta consultaSalva = consulaRepository.save(consulta);
        return "OK";
    }

    private ConsultaResponseDTO consultaResponseDTO(Consulta consulta) {
        return ConsultaResponseDTO.builder()
                .paciente(consulta.getPaciente())
                .dentista(consulta.getDentista())
                .dataHora(consulta.getDataHora())
                .build();
    }

    private Consulta toConsulta(ConsultaRequestDTO requestDTO) {
        return Consulta.builder()
                .paciente(requestDTO.getPaciente())
                .dentista(requestDTO.getDentista())
                .dataHora(requestDTO.getDataHora())
                .build();
    }

    public ConsultaResponseDTO modificar(Integer id, ConsultaRequestDTO requestDTO) throws SQLException{
        Consulta entity = toConsulta(requestDTO);
        Consulta consultaBuscado = consulaRepository.findById(id).orElse(null);

        validarAtributo(entity, consultaBuscado);

        Consulta consulta = consulaRepository.save(consultaBuscado);
        ConsultaResponseDTO consultaResponseDTO = consultaResponseDTO(consulta);
        return consultaResponseDTO;
    }

    public static void validarAtributo(Consulta entity, Consulta consultaBuscado) {
        if (entity.getPaciente() != null ) {
            consultaBuscado.setPaciente(entity.getPaciente());
        }

        if (entity.getDentista() != null) {
            consultaBuscado.setDentista(entity.getDentista());
        }

        if (entity.getDataHora() != null) {
            consultaBuscado.setDataHora(entity.getDataHora());
        }
    }

    public void excluir(Integer id) { consulaRepository.deleteById(id);}

    public List<Consulta> buscarTodos() {return consulaRepository.findAll();}

    public Optional<Consulta> buscarId(Integer id) { return consulaRepository.findById(id); }

    public Optional<Consulta> buscarPorDataHora(LocalDateTime dataHora) {
        return consulaRepository.findByDataHora(dataHora);
    }
}

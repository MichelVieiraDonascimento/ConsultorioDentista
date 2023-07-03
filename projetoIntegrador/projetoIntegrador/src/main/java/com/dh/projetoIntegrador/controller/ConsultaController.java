package com.dh.projetoIntegrador.controller;

import com.dh.projetoIntegrador.dto.response.CadastrarConsultaResponseDTO;
import com.dh.projetoIntegrador.dto.response.ConsultaResponseDTO;
import com.dh.projetoIntegrador.exception.InvalidDataException;
import com.dh.projetoIntegrador.exception.ResourceNotFoundException;
import com.dh.projetoIntegrador.model.Consulta;
import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.model.Paciente;
import com.dh.projetoIntegrador.repository.DentistaRepository;
import com.dh.projetoIntegrador.repository.PacienteRepository;
import com.dh.projetoIntegrador.service.ConsultaService;
import com.dh.projetoIntegrador.service.PacienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;


    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private  final Logger log = LogManager.getLogger(PacienteService.class);

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CadastrarConsultaResponseDTO cadastrarConsultaResponseDTO) throws ResourceNotFoundException{

        log.info("Cadastrando uma nova consulta");

        Optional<Dentista> optionalDentista = dentistaRepository.findJogadorByMatricula(cadastrarConsultaResponseDTO.getMatricula());
        optionalDentista.orElseThrow(() -> new  ResourceNotFoundException("Dentista não encontrado!"));


        Optional<Paciente> optionalPaciente = pacienteRepository.findJogadorByRgContainingIgnoreCase(cadastrarConsultaResponseDTO.getRg());
        optionalPaciente.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));


        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();
        consultaResponseDTO.setDentista(optionalDentista.get());
        consultaResponseDTO.setPaciente(optionalPaciente.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        consultaResponseDTO.setDataHora(LocalDateTime.parse(cadastrarConsultaResponseDTO.getDataHora(), formatter));
        System.out.println(cadastrarConsultaResponseDTO.getDataHora());

        String consultaSalva = consultaService.salvar(new Consulta(consultaResponseDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body("Consulta criado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Consulta>> buscarTodos() {
        log.info("Listando todos as consultas");
        List<Consulta> body = consultaService.buscarTodos();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity <Consulta> buscarPorId(@PathVariable Integer id){
        log.info("Listando a consulta de id: " + id );
        Optional<Consulta> body = consultaService.buscarId(id);
        return ResponseEntity.ok(body.get());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar (@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Consulta> consulta = consultaService.buscarId(id);
        log.info("Deletando a consulta de id: " + id);

        try {
            consultaService.excluir(id);
            log.info("Consulta deletada com sucesso!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new ResourceNotFoundException("Ouve algum erro ao deletar a consulta de id: " + id);
        }
    }



}

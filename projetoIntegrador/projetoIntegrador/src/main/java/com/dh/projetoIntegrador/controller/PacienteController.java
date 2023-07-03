package com.dh.projetoIntegrador.controller;



import com.dh.projetoIntegrador.dto.request.PacienteRequestDTO;
import com.dh.projetoIntegrador.dto.response.PacienteResponseDTO;
import com.dh.projetoIntegrador.exception.ResourceNotFoundException;
import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.model.Paciente;
import com.dh.projetoIntegrador.service.PacienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    private  final Logger log = LogManager.getLogger(PacienteService.class);

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody PacienteResponseDTO paciente ) throws SQLException {
        log.info("Registrando um novo paciente!");
        if (paciente.getNome() != null && !paciente.getNome().isEmpty()) {
            pacienteService.salvar(new Paciente(paciente));
            log.info("Paciente registrado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente criado com sucesso!");
        }else {
            log.error("Ouve algum erro ao registrar o paciente!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<PacienteResponseDTO> put(@RequestBody PacienteRequestDTO requestDTO) throws SQLException {
        log.info("Modificando o paciente");
        if (requestDTO.getId() != null && pacienteService.buscarId(requestDTO.getId()).isPresent()) {
            log.info("Paciente modificado com sucesso!");
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.modificar(requestDTO.getId(), requestDTO));
        } else {
            log.error("Ouve algum erro ao modificar o paciente!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar (@PathVariable Integer id) throws ResourceNotFoundException {
        log.info("Deletando o paciente de id: " + id);
        Optional<Paciente> paciente = pacienteService.buscarId(id);

        try {
            log.info("Paciente deletado com sucesso!");
            pacienteService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new ResourceNotFoundException("Ouve algum erro ao deletar o paciente de id: " + id);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> buscarTodos() {
        log.info("Listando todos os pacientes");
        List<Paciente> body = pacienteService.buscarTodos();
        return ResponseEntity.ok(body);

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity <Paciente> buscarPorId(@PathVariable Integer id){
        log.info("Listando o paciente de id: " + id );
        Optional<Paciente> body = pacienteService.buscarId(id);
        return ResponseEntity.ok(body.get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Optional<Paciente>> buscarPorNome(@PathVariable String nome){
        log.info("Listando o paciente de nome: " + nome);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarPorNome(nome));
    }
}
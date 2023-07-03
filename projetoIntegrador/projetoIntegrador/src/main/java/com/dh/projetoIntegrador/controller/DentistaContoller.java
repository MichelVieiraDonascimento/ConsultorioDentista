package com.dh.projetoIntegrador.controller;


import com.dh.projetoIntegrador.dto.request.DentistaRequestDTO;
import com.dh.projetoIntegrador.dto.response.DentistaResponseDTO;
import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.service.DentistaService;
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
@RequestMapping("/dentista")
public class DentistaContoller {

    @Autowired
    private DentistaService dentistaService;
    private final Logger log = LogManager.getLogger(DentistaService.class);

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody DentistaResponseDTO dentista ) throws SQLException {
        log.info("Registrando um novo dentista!");
        if (dentista.getNome() != null && !dentista.getNome().isEmpty()) {

            dentistaService.salvar(new Dentista(dentista));
            log.info("Dentista registrado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body("Dentista criado com sucesso!");

        }else {
            log.error("Ouve algum erro ao registrar o dentista!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @PutMapping("/modificar")
    public ResponseEntity <DentistaResponseDTO> put(@RequestBody DentistaRequestDTO requestDTO) throws SQLException{
        log.info("Modificando o dentista");
        if ( requestDTO.getId() != null && dentistaService.buscarId(requestDTO.getId()).isPresent()) {
            log.info("Dentista modificado com sucesso!");
            return ResponseEntity.status(HttpStatus.OK).body(dentistaService.modificar(requestDTO.getId(), requestDTO));
        }else {
            log.error("Ouve algum erro ao modificar o dentista!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar (@PathVariable Integer id) throws SQLException{
        Optional<Dentista> dentista = dentistaService.buscarId(id);
        log.info("Deletando o dentista de id: " + id);
        if (dentista.isPresent()) {
            dentistaService.excluir(id);
            log.info("Dentista deletado com sucesso!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            log.error("Ouve algum erro ao deletar o dentista!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Dentista>> buscarTodos() {
        log.info("Listando todos os dentistas");
        List<Dentista> body = dentistaService.buscarTodos();
            return ResponseEntity.ok(body);

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity <Dentista> buscarPorId(@PathVariable Integer id){
        log.info("Listando o dentista de id: " + id );
        Optional<Dentista> body = dentistaService.buscarId(id);
        return ResponseEntity.ok(body.get());
    }
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Optional<Dentista>> buscarPorMatricula(@PathVariable String matricula){
        log.info("Listando o dentista de matricula: " + matricula);
        return ResponseEntity.status(HttpStatus.OK).body(dentistaService.buscarPorMatricula(matricula));
    }
}

package com.dh.projetoIntegrador.controller;


import com.dh.projetoIntegrador.dto.request.UsuarioLoginDto;
import com.dh.projetoIntegrador.dto.request.UsuarioRequestDto;
import com.dh.projetoIntegrador.dto.response.UsuarioResponseDto;
import com.dh.projetoIntegrador.model.Usuario;
import com.dh.projetoIntegrador.security.TokenDto;
import com.dh.projetoIntegrador.security.TokenService;
import com.dh.projetoIntegrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioLoginDto.getLogin(), usuarioLoginDto.getSenha());
        Authentication authenticate = manager.authenticate(authenticationToken);
        String tokenJwt = tokenService.gerarToken((Usuario) authenticate.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJwt));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioRequestDto));
    }

}

package com.dh.projetoIntegrador.service.impl;


import com.dh.projetoIntegrador.dto.request.UsuarioRequestDto;
import com.dh.projetoIntegrador.dto.response.UsuarioResponseDto;
import com.dh.projetoIntegrador.model.Usuario;
import com.dh.projetoIntegrador.repository.UsuarioRepository;
import com.dh.projetoIntegrador.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDto salvar(UsuarioRequestDto usuarioRequestDto) {
        ObjectMapper mapper = new ObjectMapper();
        Usuario usuarioModel = mapper.convertValue(usuarioRequestDto, Usuario.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuarioModel.getSenha());
        usuarioModel.setSenha(senhaCriptografada);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioModel);
        UsuarioResponseDto usuarioResponseDto = mapper.convertValue(usuarioSalvo, UsuarioResponseDto.class);
        return usuarioResponseDto;
    }
}

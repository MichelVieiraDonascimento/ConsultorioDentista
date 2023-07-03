package com.dh.projetoIntegrador.service;

import com.dh.projetoIntegrador.dto.request.UsuarioRequestDto;
import com.dh.projetoIntegrador.dto.response.UsuarioResponseDto;

public interface UsuarioService {
    UsuarioResponseDto salvar(UsuarioRequestDto usuarioRequestDto);
}

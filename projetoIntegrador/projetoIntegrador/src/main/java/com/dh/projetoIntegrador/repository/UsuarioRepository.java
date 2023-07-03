package com.dh.projetoIntegrador.repository;

import com.dh.projetoIntegrador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
    UserDetails findByLogin(String login);
}

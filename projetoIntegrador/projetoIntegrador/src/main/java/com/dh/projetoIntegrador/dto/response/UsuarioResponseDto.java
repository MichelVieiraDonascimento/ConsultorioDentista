package com.dh.projetoIntegrador.dto.response;
import com.dh.projetoIntegrador.security.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioResponseDto {

    private Long id;

    private String login;

    private String senha;

    private UsuarioRole usuarioRole;
}

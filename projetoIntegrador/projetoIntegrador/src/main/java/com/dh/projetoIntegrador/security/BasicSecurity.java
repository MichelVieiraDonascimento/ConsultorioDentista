package com.dh.projetoIntegrador.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class BasicSecurity {

    @Autowired
    private SecurityFilter securityFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()
                .antMatchers(HttpMethod.POST, "/dentista").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista/deletar/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista/modificar").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista/listar").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista/listar/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista/matricula/{matricula}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente/modificar").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente/deletar/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente/listar").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente/listar/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente/nome/{nome}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

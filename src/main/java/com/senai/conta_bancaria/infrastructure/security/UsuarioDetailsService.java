package com.senai.conta_bancaria.infrastructure.security;

import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.exception.UsuarioNaoEncontradoException;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Agora o 'usuario' é reconhecido como a sua Entidade Usuario
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        return new User(
                usuario.getEmail(),    // 1º parâmetro: String (username/email)
                usuario.getSenha(),    // 2º parâmetro: String (senha criptografada)
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name())) // 3º parâmetro: Autoridades
        );
    }
}

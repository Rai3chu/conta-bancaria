package com.senai.conta_bancaria.domain.repository;

import com.senai.conta_bancaria.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Mude de Optional<Object> para Optional<Usuario>
    Optional<Usuario> findByEmail(String email);
}

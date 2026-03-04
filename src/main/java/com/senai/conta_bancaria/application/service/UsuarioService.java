package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario cadastrarUsuario(Usuario usuario) {

        return cadastrarUsuario(usuario);
    }
}

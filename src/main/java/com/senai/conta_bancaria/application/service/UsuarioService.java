package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorID(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario){
        Usuario usuarioAtualizado = buscarUsuarioPorID(id);
        if (usuarioAtualizado != null) {
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setEmail(usuario.getEmail());
            usuarioAtualizado.setSenha(usuario.getSenha());
            return usuarioRepository.save(usuarioAtualizado);
        }
        return null;
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioDto;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public String insert(UsuarioDto usuarioDto) {
        if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmacaoSenha())) {
            System.out.println("senhas diferentes");
            return "redirect:/usuarios";
        }

        Usuario usuario = Usuario.builder()
                .login(usuarioDto.getLogin())
                .senha(usuarioDto.getSenha())
                .cargo(usuarioDto.getCargo())
                .build();

        repository.insert(usuario);
        System.out.println("usuário cadastrado com sucesso!");
        return "redirect:/usuarios";
    }
}
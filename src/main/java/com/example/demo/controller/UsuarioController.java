package com.example.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Usuario;
import com.example.demo.dto.UsuarioDto;
import com.example.demo.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public String insert(UsuarioDto usuarioDto) {
        return service.insert(usuarioDto);
    }

    @GetMapping
    public ModelAndView telaCadastroUsuario() throws SQLException {
        ModelAndView mv = new ModelAndView("cadastro_usuario");
        mv.addObject("cargos", Usuario.getAllCargos());
        return mv;
    }
}
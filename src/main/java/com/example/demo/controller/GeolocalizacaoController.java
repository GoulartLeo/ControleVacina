package com.example.demo.controller;

import com.example.demo.domain.Bairro;
import com.example.demo.services.VacinaBairroService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class GeolocalizacaoController {

    @Autowired
    private VacinaBairroService vacinaBairroService;

    @GetMapping("/geolocalizacao/{nomeBairro}")
    public boolean obterBairro(@PathVariable String nomeBairro, HttpSession session) throws Exception {
        Bairro bairroNaSessao = (Bairro) session.getAttribute("bairroNaSessao");
        if (bairroNaSessao == null || !bairroNaSessao.getNome().equalsIgnoreCase(nomeBairro)) {
            Bairro bairroNoBanco = vacinaBairroService.buscarBairroPorNome(nomeBairro);
            if (bairroNoBanco != null && bairroNoBanco.getId() == null) {
                throw new Exception("Bairro não encontrado");
            }
            session.setAttribute("bairroNaSessao", bairroNoBanco);
            return true;
        }
        return false;
    }
}

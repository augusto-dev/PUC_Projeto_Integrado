package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.domain.Conta;
import com.pucminas.easyfarma.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContaController {

    @Autowired
    ContaService service;

    @GetMapping("/login")
    public String loginScreen() {
        return "login";
    }

    @GetMapping("/register")
    public String registerScreen() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView login(@RequestParam("username") String nome,
                              @RequestParam("password") String senha) {
        Conta conta = service.findByName(nome);
        boolean invalid = nome == null || nome.isEmpty() || nome.isBlank() || senha == null || senha.isBlank() || senha.isEmpty();

        if (conta != null || invalid) {
            ModelAndView mv = new ModelAndView("redirect:/register");

            if (invalid) {
                mv.addObject("invalidForm", true);
            } else {
                mv.addObject("error", true);
            }
            return mv;
        } else {
            Conta novaConta = new Conta();
            novaConta.setNome(nome);
            novaConta.setSenha(new BCryptPasswordEncoder().encode(senha));
            service.insert(novaConta);
        }
        return new ModelAndView("redirect:/login?registerSuccess");
    }
}

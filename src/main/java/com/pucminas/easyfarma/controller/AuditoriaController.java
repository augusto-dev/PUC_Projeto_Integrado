package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.domain.Auditoria;
import com.pucminas.easyfarma.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/auditoria")
public class AuditoriaController {

    @Autowired
    AuditoriaService service;

    @GetMapping("")
    public ModelAndView findAll() {
        List<Auditoria> obj = service.findAll();
        System.out.println("dados: "+obj);
        ModelAndView mv = new ModelAndView("auditoria/index");
        mv.addObject("auditorias", obj);
        return mv;
    }
}

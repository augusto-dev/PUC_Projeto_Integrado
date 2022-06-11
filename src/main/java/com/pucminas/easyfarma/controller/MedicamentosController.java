package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.service.AuditoriaService;
import com.pucminas.easyfarma.util.AuditUtils;
import com.pucminas.easyfarma.domain.Medicamentos;
import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import com.pucminas.easyfarma.dto.RequisicaoFormMedicamentos;
import com.pucminas.easyfarma.service.MedicamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/medicamentos")
public class MedicamentosController {

    @Autowired
    MedicamentosService service;
    @Autowired
    AuditoriaService auditoriaService;

    @GetMapping("")
    public ModelAndView findAll() {
        List<Medicamentos> obj = service.findAll();

        ModelAndView mv = new ModelAndView("medicamentos/index");
        mv.addObject("medicamentos", obj);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView insert(RequisicaoFormMedicamentos requisicao) {
        return new ModelAndView("medicamentos/new");
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Integer id) {
        Medicamentos medicamento = this.service.find(id);

        if (medicamento != null) {
            ModelAndView mv = new ModelAndView("medicamentos/show");
            mv.addObject("medicamento", medicamento);
            return mv;
        } else {
            return this.retornaErroMedicamento("medicamento nao encontrado: " + id);
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Integer id, @Valid RequisicaoFormMedicamentos requisicao, BindingResult bindingResult) {
        System.out.println("Requisicao: " + requisicao);

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("medicamentos/edit");
            mv.addObject("medicamentoId", id);
            mv.addObject("error", true);
            return mv;
        } else {
            Medicamentos medicamento = service.find(id);

            if (medicamento != null) {
                Medicamentos toUpdate = requisicao.toMedicamento();
                toUpdate.setId(medicamento.getId());
                service.update(toUpdate);
                AuditUtils.saveAudit(auditoriaService, Action.EDITOU, Item.MEDICAMENTO, medicamento.getId(), medicamento.getNome());
                return new ModelAndView("redirect:/medicamentos/" + medicamento.getId());
            } else {
                return this.retornaErroMedicamento("nao encontrado");
            }
        }
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormMedicamentos requisicao, BindingResult bindingResult) {
        System.out.println(requisicao);

        if (bindingResult.hasErrors()) {
            System.out.println("erros form create medicamentos");
            ModelAndView mv = new ModelAndView("medicamentos/new");
            mv.addObject("error", true);
            return mv;
        } else {
            Medicamentos medicamento = requisicao.toMedicamento();
            this.service.insert(medicamento);
            AuditUtils.saveAudit(auditoriaService, Action.CRIOU, Item.MEDICAMENTO, medicamento.getId(), medicamento.getNome());
            return new ModelAndView("redirect:/medicamentos/" + medicamento.getId());
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, RequisicaoFormMedicamentos requisicao) {
        Medicamentos medicamento = service.find(id);

        if (medicamento != null) {
            requisicao.fromMedicamento(medicamento);
            ModelAndView mv = new ModelAndView("medicamentos/edit");
            mv.addObject("medicamentoId", medicamento.getId());
            return mv;
        } else {
            System.out.println("medicamento não encontrado: " + id);
            return this.retornaErroMedicamento("Não encontrado!");
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/medicamentos");

        try {
            Medicamentos medicamento = service.find(id);
            String name = "";

            if (medicamento != null) name = medicamento.getNome();
            service.delete(id);
            AuditUtils.saveAudit(auditoriaService, Action.REMOVEU, Item.MEDICAMENTO, id, name);
            mv.addObject("mensagem", "Medicamento #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (Exception err) {
            System.out.println(err);
            mv = this.retornaErroMedicamento("Erro ao deletar medicamento");
        }
        return mv;
    }

    private ModelAndView retornaErroMedicamento(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/medicamentos");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}

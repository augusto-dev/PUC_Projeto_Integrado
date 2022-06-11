package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.domain.Procedimento;
import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import com.pucminas.easyfarma.dto.RequisicaoFormProcedimentos;
import com.pucminas.easyfarma.service.AuditoriaService;
import com.pucminas.easyfarma.service.PessoaService;
import com.pucminas.easyfarma.service.ProcedimentoService;
import com.pucminas.easyfarma.util.AuditUtils;
import org.apache.tomcat.jni.Proc;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/procedimentos")
public class ProcedimentoController {

    private final String MEDICOS_FIELD = "medicos_field";
    private final String ENFERMEIROS_FIELD = "enfermeiros_field";
    private final String PACIENTES_FIELD = "pacientes_field";

    @Autowired
    ProcedimentoService service;
    @Autowired
    PessoaService pessoaService;
    @Autowired
    AuditoriaService auditoriaService;

    @GetMapping("")
    public ModelAndView findAll() {
        List<Procedimento> obj = service.findAll();
        System.out.println("procedimentos: " + obj);

        ModelAndView mv = new ModelAndView("procedimentos/index");
        mv.addObject("procedimentos", obj);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Integer id) {
        Procedimento procedimento = service.find(id);

        if (procedimento != null) {
            ModelAndView mv = new ModelAndView("procedimentos/show");
            mv.addObject("procedimento", procedimento);
            return mv;
        } else {
            return this.retornaErroProcedimento("procedimento #" + id + " não encontrado!");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editScreen(@PathVariable Integer id, RequisicaoFormProcedimentos requisicao) {
        Procedimento procedimento = service.find(id);

        if (procedimento != null) {
            requisicao.fromProcedimento(procedimento);

            ModelAndView mv = buildData("procedimentos/edit");
            mv.addObject("procedimentoId", procedimento.getId());
            return mv;
        } else {
            return this.retornaErroProcedimento("Procedimento #" + id + " não encontrado!");
        }
    }

    @GetMapping(value = "/new")
    public ModelAndView createScreen(RequisicaoFormProcedimentos requisicao) {
        return buildData("procedimentos/new");
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProcedimentos requisicao, BindingResult bindingResult) {
        System.out.println("Requisicao: " + requisicao);

        if (bindingResult.hasErrors()) {
            System.out.println("Errors: " + bindingResult.getAllErrors().toString());
            ModelAndView mv = buildData("procedimentos/new");
            mv.addObject("error", true);
            return mv;
        } else {
            Procedimento procedimento = requisicao.toProcedimento();
            System.out.println("procedimento: " + procedimento);
            this.service.insert(procedimento);
            AuditUtils.saveAudit(auditoriaService, Action.CRIOU, Item.PROCEDIMENTO, procedimento.getId(), procedimento.getDescricao());
            return new ModelAndView("redirect:/procedimentos/" + procedimento.getId());
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Integer id, @Valid RequisicaoFormProcedimentos requisicao, BindingResult bindingResult) {
        System.out.println("Requisicao: " + requisicao);

        if (bindingResult.hasErrors()) {
            ModelAndView mv = buildData("procedimentos/edit");
            mv.addObject("procedimentoId", id);
            mv.addObject("error", true);
            return mv;
        } else {
            Procedimento procedimento = service.find(id);

            if (procedimento != null) {
                Procedimento toUpdate = requisicao.toProcedimento();
                toUpdate.setId(procedimento.getId());
                service.update(toUpdate);
                AuditUtils.saveAudit(auditoriaService, Action.EDITOU, Item.PROCEDIMENTO, procedimento.getId(), procedimento.getDescricao());

                return new ModelAndView("redirect:/procedimentos/" + procedimento.getId());
            } else {
                return retornaErroProcedimento("Procedimento #" + id + " não encontrado!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/procedimentos");

        try {
            String desc = "";
            Procedimento procedimento = service.find(id);
            if (procedimento != null) desc = procedimento.getDescricao();
            service.delete(id);
            AuditUtils.saveAudit(auditoriaService, Action.REMOVEU, Item.PROCEDIMENTO, id, desc);
            mv.addObject("mensagem", "Procedimento #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        } catch (Exception e) {
            mv = this.retornaErroProcedimento("Procedimento #" + id + " não encontrado");
        }
        return mv;
    }

    private ModelAndView buildData(String screen) {
        ModelAndView mv = new ModelAndView(screen);
        mv.addObject(MEDICOS_FIELD, pessoaService.findAllByType(TipoPessoa.MEDICO));
        mv.addObject(ENFERMEIROS_FIELD, pessoaService.findAllByType(TipoPessoa.ENFERMEIRO));
        mv.addObject(PACIENTES_FIELD, pessoaService.findAllByType(TipoPessoa.PACIENTE));
        return mv;
    }

    private ModelAndView retornaErroProcedimento(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/procedimentos");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}

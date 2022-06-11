package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.domain.Pessoa;
import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import com.pucminas.easyfarma.dto.RequisicaoFormPessoa;
import com.pucminas.easyfarma.service.AuditoriaService;
import com.pucminas.easyfarma.service.PessoaService;
import com.pucminas.easyfarma.service.exceptions.DataIntegrityException;
import com.pucminas.easyfarma.util.AuditUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/pessoas")
public class PessoaController {

	private final String TIPO_PESSOA_FIELD = "listaTipoPessoas";

	@Autowired
	PessoaService service;
	@Autowired
	AuditoriaService auditoriaService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView findAll() {
		List<Pessoa> obj = service.findAll();

		ModelAndView mv = new ModelAndView("pessoas/index");
		mv.addObject("pessoas", obj);
		return mv;
	}

	@GetMapping(value = "/new")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView insert(RequisicaoFormPessoa requisicao) {
		ModelAndView mv = new ModelAndView("pessoas/new");
		mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Void> update(@RequestBody Pessoa obj, @PathVariable Integer id) {
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView show(@PathVariable Integer id) {
		Pessoa pessoa = this.service.find(id);

		if (pessoa != null) {
			ModelAndView mv = new ModelAndView("pessoas/show");
			mv.addObject("pessoa", pessoa);

			return mv;
		}
		// não achou um registro na tabela pessoa com o id informado
		else {
			System.out.println("$$$$$$$$$$$ NÃO ACHOU A PESSOA DE ID "+ id + " $$$$$$$$$$$");
			return this.retornaErroPessoa("pessoa #" + id + " não encontrado no banco!");
		}
	}

	@PostMapping("")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView create(@Valid RequisicaoFormPessoa requisicao, BindingResult bindingResult) {
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************* TEM ERROS ***************\n");

			ModelAndView mv = new ModelAndView("pessoas/new");
			mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());
			mv.addObject("error", true);
			return mv;
		} else {
			Pessoa pessoa = requisicao.toPessoa();
			this.service.insert(pessoa);
			AuditUtils.saveAudit(auditoriaService, Action.CRIOU, Item.PESSOA, pessoa.getId(), pessoa.getNome());

			return new ModelAndView("redirect:/pessoas/" + pessoa.getId());
		}
	}

	@GetMapping("/{id}/edit")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView edit(@PathVariable Integer id, RequisicaoFormPessoa requisicao) {
		Pessoa pessoa = service.find(id);

		if (pessoa != null) {

			requisicao.fromPessoa(pessoa);

			ModelAndView mv = new ModelAndView("pessoas/edit");
			mv.addObject("pessoaId", pessoa.getId());
			mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());

			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ NÃO ACHOU A PESSOA DE ID "+ id + " $$$$$$$$$$$");
			return this.retornaErroPessoa("pessoa #" + id + " não encontrado no banco!");
		}
	}

	@PostMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView update(@PathVariable Integer id, @Valid RequisicaoFormPessoa requisicao, BindingResult bindingResult) {
		System.out.println("requisicao: " + requisicao.toString());

		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("pessoas/edit");
			mv.addObject("pessoaId", id);
			mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());
			mv.addObject("error", true);
			return mv;
		}
		else {
			Pessoa pessoa = service.find(id);

			if (pessoa != null) {
				Pessoa toUpdate = requisicao.toPessoa();
				toUpdate.setId(pessoa.getId());
				service.update(toUpdate);
				AuditUtils.saveAudit(auditoriaService, Action.EDITOU, Item.PESSOA, pessoa.getId(), pessoa.getNome());

				return new ModelAndView("redirect:/pessoas/" + pessoa.getId());
			}
			// não achou um registro na tabela Pessoa com o id informado
			else {
				System.out.println("############ NÃO ACHOU A PESSOA DE ID "+ id + " ############");
				return this.retornaErroPessoa("UPDATE ERROR: Pessoa #" + id + " não encontrado no banco!");
			}
		}
	}

	@GetMapping("/{id}/delete")
	@PreAuthorize("hasAuthority('USER')")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/pessoas");

		try {
			String name = "";
			Pessoa pessoa = service.find(id);
			if (pessoa != null) name = pessoa.getNome();
			service.delete(id);
			AuditUtils.saveAudit(auditoriaService, Action.REMOVEU, Item.PESSOA, id, name);
			mv.addObject("mensagem", "Pessoa #" + id + " deletado com sucesso!");
			mv.addObject("erro", false);
		}
		catch (Exception e) {
			String message = "DELETE ERROR: Pessoa #" + id + " não encontrado no banco!";

			if (e instanceof DataIntegrityException) {
				message = "Pessoa não pode ser deletada, verifique se está associado(a) a um procedimento";
			}
			System.out.println(e);
			mv = this.retornaErroPessoa(message);
		}

		return mv;
	}

	private ModelAndView retornaErroPessoa(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/pessoas");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}

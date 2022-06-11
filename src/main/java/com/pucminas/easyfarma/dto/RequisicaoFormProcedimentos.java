package com.pucminas.easyfarma.dto;

import com.pucminas.easyfarma.domain.Pessoa;
import com.pucminas.easyfarma.domain.Procedimento;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RequisicaoFormProcedimentos {
    @NotNull
    @NotEmpty
    @NotBlank
    private String descricao;
    @NotNull
    private Pessoa medico;
    @NotNull
    private Pessoa enfermeiro;
    @NotNull
    private Pessoa paciente;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    @NotNull
    private int preco;

    public Procedimento toProcedimento() {
        Procedimento procedimento = new Procedimento();
        procedimento.setDescricao(this.descricao);
        procedimento.setMedico(this.medico);
        procedimento.setEnfermeiro(this.enfermeiro);
        procedimento.setPaciente(this.paciente);
        procedimento.setData(this.data);
        procedimento.setPreco(this.preco);

        return procedimento;
    }

    public void fromProcedimento(Procedimento procedimento) {
        this.descricao = procedimento.getDescricao();
        this.medico = procedimento.getMedico();
        this.enfermeiro = procedimento.getEnfermeiro();
        this.paciente = procedimento.getPaciente();
        this.data = procedimento.getData();
        this.preco = procedimento.getPreco();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getMedico() {
        return medico;
    }

    public void setMedico(Pessoa medico) {
        this.medico = medico;
    }

    public Pessoa getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(Pessoa enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

    public Pessoa getPaciente() {
        return paciente;
    }

    public void setPaciente(Pessoa paciente) {
        this.paciente = paciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "RequisicaoFormProcedimentos{" +
                "descricao='" + descricao + '\'' +
                ", medico=" + medico +
                ", enfermeiro=" + enfermeiro +
                ", paciente=" + paciente +
                ", data=" + data +
                ", preco=" + preco +
                '}';
    }
}

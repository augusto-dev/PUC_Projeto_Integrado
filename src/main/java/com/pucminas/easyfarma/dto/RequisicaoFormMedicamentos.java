package com.pucminas.easyfarma.dto;

import com.pucminas.easyfarma.domain.Medicamentos;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RequisicaoFormMedicamentos {
    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;
    @NotNull
    @NotEmpty
    @NotBlank
    private String descricao;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_fabricacao;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_vencimento;
    @NotNull
    private Integer quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(Date data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Medicamentos toMedicamento() {
        Medicamentos medicamento = new Medicamentos();
        medicamento.setNome(this.nome);
        medicamento.setDescricao(this.descricao);
        medicamento.setData_fabricacao(this.data_fabricacao);
        medicamento.setData_vencimento(this.data_vencimento);
        medicamento.setQuantidade(this.quantidade);
        return medicamento;
    }

    public void fromMedicamento(Medicamentos medicamento) {
        this.nome = medicamento.getNome();
        this.descricao = medicamento.getDescricao();
        this.data_fabricacao = medicamento.getData_fabricacao();
        this.data_vencimento = medicamento.getData_vencimento();
        this.quantidade = medicamento.getQuantidade();
    }

    @Override
    public String toString() {
        return "RequisicaoFormMedicamentos{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data_fabricacao=" + data_fabricacao +
                ", data_vencimento=" + data_vencimento +
                ", quantidade=" + quantidade +
                '}';
    }
}

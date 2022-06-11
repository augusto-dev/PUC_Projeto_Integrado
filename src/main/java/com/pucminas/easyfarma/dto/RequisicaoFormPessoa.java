package com.pucminas.easyfarma.dto;

import com.pucminas.easyfarma.domain.Pessoa;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RequisicaoFormPessoa {
    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_nascimento;
    @NotNull
    private TipoPessoa tipoPessoa;
    @NotNull
    @NotEmpty
    @NotBlank
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String numero;
    @NotNull
    @NotEmpty
    @NotBlank
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Pessoa toPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setTipoPessoa(this.tipoPessoa);
        pessoa.setEmail(this.email);
        pessoa.setNumero(this.numero);
        pessoa.setEndereco(this.endereco);
        pessoa.setData_nascimento(this.data_nascimento);

        return pessoa;
    }

    public void fromPessoa(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.tipoPessoa = pessoa.getTipoPessoa();
        this.email = pessoa.getEmail();
        this.numero = pessoa.getNumero();
        this.endereco = pessoa.getEndereco();
        this.data_nascimento = pessoa.getData_nascimento();
    }

    @Override
    public String toString() {
        return "RequisicaoFormPessoa{" +
                "nome='" + nome + '\'' +
                ", data_nascimento=" + data_nascimento +
                ", tipoPessoa=" + tipoPessoa +
                ", email='" + email + '\'' +
                ", numero='" + numero + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}

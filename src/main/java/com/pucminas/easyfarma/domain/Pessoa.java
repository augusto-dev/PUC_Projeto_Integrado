package com.pucminas.easyfarma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_nascimento;
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    private String email;
    private String numero;
    private String endereco;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Procedimento medico;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Procedimento enfermeiro;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Procedimento paciente;

    public Pessoa() {

    }

    public Pessoa(Integer id, String nome, Date data_nascimento,
                  TipoPessoa tipo_pessoa, String email, String numero, String endereco) {
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.tipoPessoa = tipo_pessoa;
        this.email = email;
        this.numero = numero;
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setTipoPessoa(TipoPessoa tipo_pessoa) {
        this.tipoPessoa = tipo_pessoa;
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

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data_nascimento=" + data_nascimento +
                ", tipoPessoa=" + tipoPessoa +
                ", email='" + email + '\'' +
                ", numero='" + numero + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}

package com.pucminas.easyfarma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Procedimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Pessoa medico;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_enfermeiro")
    private Pessoa enfermeiro;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Pessoa paciente;
    private Date data;
    private int preco;

    public Procedimento() {
    }

    public Procedimento(Integer id, String descricao, Pessoa medico, Pessoa enfermeiro,
                        Pessoa paciente, Date data, int preco) {
        this.id = id;
        this.descricao = descricao;
        this.medico = medico;
        this.enfermeiro = enfermeiro;
        this.paciente = paciente;
        this.data = data;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Procedimento other = (Procedimento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Procedimento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", medico=" + medico +
                ", enfermeiro=" + enfermeiro +
                ", paciente=" + paciente +
                ", data=" + data +
                ", preco=" + preco +
                '}';
    }
}

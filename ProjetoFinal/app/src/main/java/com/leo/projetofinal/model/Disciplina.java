package com.leo.projetofinal.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Leo on 2/17/2018.
 */

@Entity
public class Disciplina {
    @Id
    private Long idDisciplina;
    private String nomeDisciplina;
    private String nomeProfessor;
    private Integer quantidadeDeBimestres;
    private ToMany<Bimestre> bimestre;
    private Double nota;
    private Double mediaPessoal;

    public Disciplina(){

    }

    public Disciplina(String nomeDisciplina, String nomeProfessor, Integer quantidadeDeSemestres, Double nota, Double mediaPessoal) {
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.quantidadeDeBimestres = quantidadeDeSemestres;
        this.nota = nota;
        this.mediaPessoal = mediaPessoal;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public ToMany<Bimestre> getBimestre() {
        return bimestre;
    }

    public void setBimestre(ToMany<Bimestre> bimestre) {
        this.bimestre = bimestre;
    }

    public long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getQuantidadeDeBimestres() {
        return quantidadeDeBimestres;
    }

    public void setQuantidadeDeBimestres(Integer quantidadeDeBimestres) {
        this.quantidadeDeBimestres = quantidadeDeBimestres;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getMediaPessoal() {
        return mediaPessoal;
    }

    public void setMediaPessoal(Double mediaPessoal) {
        this.mediaPessoal = mediaPessoal;
    }
}

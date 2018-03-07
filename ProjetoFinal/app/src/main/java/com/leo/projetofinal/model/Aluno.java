package com.leo.projetofinal.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by Leo on 2/17/2018.
 */
@Entity
public class Aluno {

    @Id
    private long idAluno;
    private String nome;
    private String escola;
    private Double mediaEscola;
    private ToMany<Disciplina> disciplina;


    public Aluno() {
    }

    public Aluno(String nome, String escola, Double mediaEscola, ToMany<Disciplina> disciplina) {
        this.nome = nome;
        this.escola = escola;
        this.mediaEscola = mediaEscola;
        this.disciplina = disciplina;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public ToMany<Disciplina> getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(ToMany<Disciplina> disciplina) {
        this.disciplina = disciplina;
    }

    public Double getMediaEscola() {
        return mediaEscola;
    }

    public void setMediaEscola(Double mediaEscola) {
        this.mediaEscola = mediaEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }
}

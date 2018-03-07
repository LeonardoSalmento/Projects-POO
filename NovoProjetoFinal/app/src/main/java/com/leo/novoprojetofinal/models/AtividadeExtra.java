package com.leo.novoprojetofinal.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Leo on 2/24/2018.
 */
@Entity
public class AtividadeExtra {
    @Id
    private long id;
    private String nome;
    private String professor;
    private String descricao;

    private ToOne<Aluno> alunoToOne;

    public ToOne<Aluno> getAlunoToOne() {
        return alunoToOne;
    }

    public void setAlunoToOne(ToOne<Aluno> alunoToOne) {
        this.alunoToOne = alunoToOne;
    }

    public AtividadeExtra() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}

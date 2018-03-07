package com.leo.novoprojetofinal.models;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Leo on 2/24/2018.
 */
@Entity
public class Aluno {
    @Id

    private long id;
    private String nome;
    private String nomeEscola;
    private Double mediaEscola;
    private Double mediaPessoal;
    private String email;
    private String senha;

    @Backlink
    private ToMany<Disciplina> disciplinas;
    private ToMany<AtividadeExtra> atividadeExtra;



    public Aluno() {
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

    public Double getMediaEscola() {
        return mediaEscola;
    }

    public void setMediaEscola(Double mediaEscola) {
        this.mediaEscola = mediaEscola;
    }

    public Double getMediaPessoal() {
        return mediaPessoal;
    }

    public void setMediaPessoal(Double mediaPessoal) {
        this.mediaPessoal = mediaPessoal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public ToMany<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ToMany<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public ToMany<AtividadeExtra> getAtividadeExtra() {
        return atividadeExtra;
    }

    public void setAtividadeExtra(ToMany<AtividadeExtra> atividadeExtra) {
        this.atividadeExtra = atividadeExtra;
    }


}

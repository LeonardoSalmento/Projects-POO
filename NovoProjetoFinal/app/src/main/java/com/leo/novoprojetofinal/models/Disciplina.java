package com.leo.novoprojetofinal.models;

import com.leo.novoprojetofinal.App;

import io.objectbox.Box;
import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by Leo on 2/24/2018.
 */
@Entity
public class Disciplina {
    @Id
    private long id;
    private String nome;
    private String apelido;
    private String professor;
    private Double mediaAtual;
    private Boolean recuperacaoFinal;
    private Double NotarecuperacaoFinal;
    private ToOne<Aluno> alunoToOne;

    @Backlink
    private ToMany<Bimestre> bimestre;

    public Disciplina() {
    }

    public Disciplina(String nome, String apelido, String professor, int quantidade) {
        this.nome = nome;
        this.apelido = apelido;
        this.professor = professor;
        gerarBimestre(quantidade);
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Double getMediaAtual() {
        return mediaAtual;
    }

    public void setMediaAtual(Double mediaAtual) {
        this.mediaAtual = mediaAtual;
    }

    public ToMany<Bimestre> getBimestre() {
        return bimestre;
    }

    public void setBimestre(ToMany<Bimestre> bimestre) {
        this.bimestre = bimestre;
    }

    public Boolean getRecuperacaoFinal() {
        return recuperacaoFinal;
    }

    public void setRecuperacaoFinal(Boolean recuperacaoFinal) {
        this.recuperacaoFinal = recuperacaoFinal;
    }

    public Double getNotarecuperacaoFinal() {
        return NotarecuperacaoFinal;
    }

    public void setNotarecuperacaoFinal(Double notarecuperacaoFinal) {
        NotarecuperacaoFinal = notarecuperacaoFinal;
    }

    public ToOne<Aluno> getAlunoToOne() {
        return alunoToOne;
    }

    public void setAlunoToOne(ToOne<Aluno> alunoToOne) {
        this.alunoToOne = alunoToOne;
    }

    private void gerarBimestre(int quantidade){
        for (int i = 0; i<quantidade; i++){
            Bimestre bimestre = new Bimestre();
            bimestre.getDisciplinaToOne().setTarget(this);

        }

    }
}

package com.leo.novoprojetofinal.models;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by Leo on 2/24/2018.
 */
@Entity
public class Bimestre {
    @Id
    private long id;
    private Double mensal;
    private Double bimestral;
    private Double recuperacao;
    private ToOne<Disciplina> disciplinaToOne;
    private Double mediaAtual;



    public Bimestre() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMensal() {
        return mensal;
    }

    public void setMensal(Double mensal) {
        this.mensal = mensal;
        obterMediaBimestre();
    }

    public Double getBimestral() {
        return bimestral;
    }

    public void setBimestral(Double bimestral) {
        this.bimestral = bimestral;
        obterMediaBimestre();
    }

    public Double getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(Double recuperacao) {
        this.recuperacao = recuperacao;
        obterMediaBimestre();
    }

    public ToOne<Disciplina> getDisciplinaToOne() {
        return disciplinaToOne;
    }

    public void setDisciplinaToOne(ToOne<Disciplina> disciplinaToOne) {
        this.disciplinaToOne = disciplinaToOne;
    }

    public Double obterMediaBimestre(){

        if (recuperacao != null && mensal != null && bimestral != null){
            if (recuperacao > (mensal + bimestral)/2){
                mediaAtual = recuperacao;
                return mediaAtual;
            }
            mediaAtual = (mensal + bimestral)/2;
            return mediaAtual;
        }else if(mensal != null && bimestral != null ){
            mediaAtual = (mensal + bimestral)/2;
            return mediaAtual;
        }else if(mensal != null ){
            mediaAtual = mensal;
            return mediaAtual;
        }
        return null;
    }

    public boolean isRecuperacao(Double mediaEscola){
        if(this.mensal + this.bimestral / 2 >= mediaEscola){
            return false;
        }
        return true;
    }

    public Double getMediaAtual(){
        return mediaAtual;
    }




}

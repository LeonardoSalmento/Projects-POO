package com.leo.projetofinal.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Leo on 2/23/2018.
 */
@Entity
public class Bimestre {
    @Id
    private long idBimestre;
    private Double notaMensal;
    private Double notaBimestral;
    private Double notaRecuperacao;

    public Bimestre() {

    }

    public long getIdBimestre() {
        return idBimestre;
    }

    public void setIdBimestre(long idBimestre) {
        this.idBimestre = idBimestre;
    }

    public Double getNotaMensal() {
        return notaMensal;
    }

    public void setNotaMensal(Double notaMensal) {
        this.notaMensal = notaMensal;
    }

    public Double getNotaBimestral() {
        return notaBimestral;
    }

    public void setNotaBimestral(Double notaBimestral) {
        this.notaBimestral = notaBimestral;
    }

    public Double getNotaRecuperacao() {
        return notaRecuperacao;
    }

    public void setNotaRecuperacao(Double notaRecuperacao) {
        this.notaRecuperacao = notaRecuperacao;
    }
}

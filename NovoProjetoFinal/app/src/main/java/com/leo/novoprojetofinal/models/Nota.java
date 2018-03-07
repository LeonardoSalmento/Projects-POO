package com.leo.novoprojetofinal.models;

import java.sql.Array;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Leo on 3/5/2018.
 */
@Entity
public class Nota {
    @Id
    private long id;
    private Double valor;
    private int tipo;

    private ToOne<Bimestre> bimestreToOne;

    public Nota() {
    }

    public String getNomeMagico(){
        String nomes[] = {"mensal", "bimestral", "recuperacao"};
        return nomes[this.tipo];
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToOne<Bimestre> getBimestreToOne() {
        return bimestreToOne;
    }

    public void setBimestreToOne(ToOne<Bimestre> bimestreToOne) {
        this.bimestreToOne = bimestreToOne;
    }


}

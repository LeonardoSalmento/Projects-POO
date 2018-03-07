package com.leo.anuncios.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by Leo on 2/12/2018.
 */
@Entity
public class Anuncio {

    @Id public long id;

    private String nome;
    private Double valor;
    private String local;
    private String data;
    private String descricao;
    private ToOne<Usuario> dono;

    public Anuncio(){

    }

    public Anuncio(String nome, Double valor, String local, String descricao) {
        this.nome = nome;
        this.valor = valor;
        this.local = local;
        this.descricao = descricao;
        this.data = getData();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        Date data = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String newDate = simpleDateFormat.format(data);
        return newDate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ToOne<Usuario> getDono() {
        return dono;
    }

    public void setDono(ToOne<Usuario> dono) {
        this.dono = dono;
    }
}

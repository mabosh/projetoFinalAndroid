package com.example.projeto01.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String nome;
    private Float valor;
    private String local;
    private Categoria categoria;

    public Evento(int id, String nome, Float valor, String local, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.local = local;
        this.categoria = categoria;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    @NonNull
    @Override
    public String toString() {

        return id + " " + nome;
    }
}

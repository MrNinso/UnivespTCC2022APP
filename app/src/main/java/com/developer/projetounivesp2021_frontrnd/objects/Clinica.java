package com.developer.projetounivesp2021_frontrnd.objects;

import java.io.Serializable;

public class Clinica implements Serializable {
    private int cid;
    private String nome;

    public Clinica(int cid, String nome) {
        this.cid = cid;
        this.nome = nome;
    }

    public int getCid() {
        return cid;
    }

    public String getNome() {
        return nome;
    }
}

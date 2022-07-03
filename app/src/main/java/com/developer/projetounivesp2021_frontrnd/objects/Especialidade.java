package com.developer.projetounivesp2021_frontrnd.objects;

import java.io.Serializable;

public class Especialidade implements Serializable {
    private int eid;
    private String nome;

    public Especialidade(int eid, String nome) {
        this.eid = eid;
        this.nome = nome;
    }

    public int getEid() {
        return eid;
    }

    public String getNome() {
        return nome;
    }
}

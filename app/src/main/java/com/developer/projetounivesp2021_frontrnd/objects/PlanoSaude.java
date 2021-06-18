package com.developer.projetounivesp2021_frontrnd.objects;

import java.io.Serializable;

public class PlanoSaude implements Serializable {
    private final int cid;
    private final long cpid;
    private final String nome;

    public PlanoSaude(int cid, long cpid, String nome) {
        this.cid = cid;
        this.cpid = cpid;
        this.nome = nome;
    }

    public int getCid() {
        return this.cid;
    }

    public long getCpid() {
        return this.cpid;
    }

    public String getNome() {
        return this.nome;
    }
}
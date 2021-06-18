package com.developer.projetounivesp2021_frontrnd.objects;

import com.developer.projetounivesp2021_frontrnd.interfaces.Constants;

import java.io.Serializable;

public class Usuario implements Constants, Serializable {
    private final String nome, email, password, cpf;
    private final long nascimento, telefone;
    public long cpid = -1;
    private final SEXO sexo;

    public Usuario(String nome, String email, String password, String cpf, long nascimento, long telefone, long cpid, SEXO sexo) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.cpid = cpid;
        this.sexo = sexo;
    }

    public Usuario(String nome, String email, String password, String cpf, long nascimento, long telefone, SEXO sexo) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpf() {
        return cpf;
    }

    public long getNascimento() {
        return nascimento;
    }

    public long getTelefone() {
        return telefone;
    }

    public long getCpid() {
        return cpid;
    }

    public SEXO getSexo() {
        return sexo;
    }
}

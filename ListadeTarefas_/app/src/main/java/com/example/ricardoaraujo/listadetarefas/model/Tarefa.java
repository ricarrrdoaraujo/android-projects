package com.example.ricardoaraujo.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {
    //Usamos o Serializable pq precisaremos transferir
    //uma tarefa direto para outra activity utilizando o putExtra()
    private  Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

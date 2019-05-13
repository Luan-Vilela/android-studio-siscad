package com.example.escola.Model;

public class Curso {

    private int id;
    private String nome;
    private int qtdHoras;

    public Curso(String nome, int qtdHoras) {
        this.nome = nome;
        this.qtdHoras = qtdHoras;
    }

    public Curso(){

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

    public int getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }
}

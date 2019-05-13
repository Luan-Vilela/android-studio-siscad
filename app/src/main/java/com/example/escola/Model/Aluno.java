package com.example.escola.Model;

public class Aluno {

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private int curso;


    public Aluno(String nome, String email, String telefone, int curso) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
    }

    public Aluno(){};

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
}

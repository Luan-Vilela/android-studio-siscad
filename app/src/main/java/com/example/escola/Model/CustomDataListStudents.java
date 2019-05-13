/*
* Classe que vai alimentar o Adapter da list do aluno
* */
package com.example.escola.Model;

public class CustomDataListStudents {

    int id;
    String nameStudent;
    String nameCourse;

    public CustomDataListStudents(int id, String nameStudent, String nameCourse) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.nameCourse = nameCourse;
    }
    public CustomDataListStudents(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}

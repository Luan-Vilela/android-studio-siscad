/*
* Classe que contém os métodos para fazer a manipulação do banco de dados
* essa classe é filha da classe Connect.
*
*/
package com.example.escola.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Crud extends Connect {


    public Crud(Context context) {
        super(context);
    }


    /* */

    public void addAluno(Aluno aluno){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        /* Adiciona o campo */
        values.put("nomeAluno", aluno.getNome() );
        values.put("emailAluno", aluno.getEmail());
        values.put("telefoneAluno", aluno.getTelefone());
        values.put("cursoId", aluno.getCurso());

        db.insert("aluno", null,  values);
        db.close();

    }

    public void removeAluno(Aluno aluno){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("aluno", "alunoId = ?", new String[]{String.valueOf(aluno.getId())}) ;
        db.close();

    }

    public Aluno selecionaAluno(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query("aluno", new String[]{"alunoId", "nomeAluno", "emailAluno", "telefoneAluno", "cursoId"},
                "alunoId = ?", new String[] {String.valueOf(id)}, null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Aluno aluno = new Aluno( cursor.getString(1), cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)));

        aluno.setId(id);
        db.close();
        return  aluno;
    }

    public void atualizarAluno(Aluno aluno){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nomeAluno", aluno.getNome() );
        values.put("emailAluno", aluno.getEmail());
        values.put("telefoneAluno", aluno.getTelefone());
        values.put("cursoId", aluno.getCurso());

        db.update("aluno", values, "alunoId = ?", new  String[] { String.valueOf(aluno.getId()) });
        db.close();
    }

    public ArrayList<CustomDataListStudents> listaTodosAlunos(){
        ArrayList<CustomDataListStudents> listaAluno = new ArrayList<CustomDataListStudents>();

        String query = "SELECT alunoId, nomeAluno, nomeCurso FROM aluno INNER JOIN curso " +
                "ON aluno.cursoId = curso.cursoId";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()){
            do{
                //custom array adapter
                CustomDataListStudents studentList = new CustomDataListStudents();
                studentList.setId(Integer.parseInt(cursor.getString(0)));
                studentList.setNameStudent(cursor.getString(1));
                studentList.setNameCourse(cursor.getString(2));

                listaAluno.add(studentList);
            }while (cursor.moveToNext());
        }

        return listaAluno;
    }

    /*
    * Methods Curso
    * */

    public void addCourse(Curso curso){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nomeCurso", curso.getNome());
        values.put("qtdHoras", curso.getQtdHoras());

        db.insert("curso", null,values);
        db.close();

    }

    public void updateCourse(Curso course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put("nomeCurso", course.getNome());
        values.put("qtdHoras", course.getQtdHoras());

        db.update("curso", values, "cursoId = ?", new  String[] { String.valueOf(course.getId()) });
        db.close();
    }


    public void rmCourse(Curso curso){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("curso", "cursoId = ?", new String[]{String.valueOf(curso.getId())});
        db.close();
    }

    public List<Curso> listCourses(){
        List<Curso> arrCourses = new ArrayList<Curso>();

        String query = "SELECT * FROM curso";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){
            do{

                Curso course = new Curso();
                course.setId( Integer.parseInt(cursor.getString(0)) );
                course.setNome(cursor.getString(1));
                course.setQtdHoras( Integer.parseInt(cursor.getString(2)) );
                arrCourses.add(course);

            }while (cursor.moveToNext());
        }

        return arrCourses;
    }

    public Curso selectOneCourse(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query("curso", new String[]{"cursoId", "nomeCurso", "qtdHoras"},
                "cursoId = ?", new String[] {String.valueOf(id)}, null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Curso course = new Curso( cursor.getString(1),cursor.getInt(2));
        course.setId(cursor.getInt(0));
        db.close();

        return  course;
    }

    public int positionCursoToSpinner(int id){
        String query = "SELECT count(*) FROM curso WHERE cursoId < " + String.valueOf(id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }



}

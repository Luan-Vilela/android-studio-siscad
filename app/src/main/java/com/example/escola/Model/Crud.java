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


    /*
    * Insere aluno na tabela aluno
    * Método recebe um objeto aluno e faz a inserção na tabela
    */

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
    /*
    *   Método remove um aluno dá tabela
    *   O método verifica se existe algum id igual do objeto e
    *   exclui caso exista.
     */
    public void removeAluno(Aluno aluno){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("aluno", "alunoId = ?", new String[]{String.valueOf(aluno.getId())}) ;
        db.close();

    }
    /*
    * Seleciona 1 aluno na tabela com base no id.
    * Retorna o objeto aluno
    * */
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
    /*
    * Atualiza aluno na tabela.
    * Método recebe um objeto completo aluno
    * e atualiza ele na tabela caso exista o id
    * */
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
    /*
    * Método devolve um arraylist de alunos e curso.
    * trabalha com as duas tabelas fazendo um join e retornando o
    * curso e nome de cada aluno.
    * */
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

    /* ***************************************************************************************
    * Methods Curso
    * */

    /*
    * Método recebe um objeto curso e insere na tabela
    * */
    public void addCourse(Curso curso){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nomeCurso", curso.getNome());
        values.put("qtdHoras", curso.getQtdHoras());

        db.insert("curso", null,values);
        db.close();

    }
    /*
    * Método atualiza curso na tabela caso exista
    * um id já cadastrado
    * */
    public void updateCourse(Curso course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nomeCurso", course.getNome());
        values.put("qtdHoras", course.getQtdHoras());

        db.update("curso", values, "cursoId = ?", new  String[] { String.valueOf(course.getId()) });
        db.close();
    }

    /*
     * Método recebe um curso e deleta ele da tabela
    * */
    public void rmCourse(Curso curso){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("curso", "cursoId = ?", new String[]{String.valueOf(curso.getId())});
        db.close();
    }
    /*
    * Retorna todos os cursos cadastrados no banco
    * */
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

    /*
    * Retorna um curso se id existir na tabela
    * recebe um int e retorna um objeto curso
    * */
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

    /*
    * Método retorna a posição do curso na tabela
    * usado para setar o curso atual do aluno no spinner.
    *
    * */
    public int positionCursoToSpinner(int id){
        String query = "SELECT count(*) FROM curso WHERE cursoId < " + String.valueOf(id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }



}

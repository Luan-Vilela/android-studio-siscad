/*
* Classe que fazer a Conexão com banco de dados
* usando o SQLiteOpenHelper
*
*   Essa classe contém dados para a criação do banco
* e das Tabelas aluno e curso
*
*/
package com.example.escola.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connect extends SQLiteOpenHelper {

    private static final String DATABASE = "CursosOnline";
    private static final int VERSIOM_DATABASE = 1;

    public static final String TABLE_ALUNO = "" +
            "CREATE TABLE IF NOT EXISTS aluno (" +
            "alunoId INTEGER PRIMARY KEY," +
            "nomeAluno VARCHAR(50) NOT NULL," +
            "emailAluno VARCHAR(80) NOT NULL," +
            "telefoneAluno VARCHAR(11)," +
            "cursoId INTEGER," +
            "FOREIGN KEY(cursoId) REFERENCES curso(cursoId)" +
            ")";

    public static final String TABLE_CURSO = "" +
            "CREATE TABLE IF NOT EXISTS curso (" +
            "cursoId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nomeCurso VARCHAR(50) NOT NULL," +
            "qtdHoras INTEGER" +
            ")";


    public Connect(Context context) {
        super(context, DATABASE, null, VERSIOM_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ALUNO);
        db.execSQL(TABLE_CURSO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS aluno");
            db.execSQL("DROP TABLE IF EXISTS curso");
            onCreate(db);
        }
    }


}

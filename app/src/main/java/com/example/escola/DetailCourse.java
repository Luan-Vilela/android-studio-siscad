/*
* Classe que faz a munipulação do objeto Curso
*
* Contém métodos para alterar e excluir do banco de dados
* */
package com.example.escola;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.escola.Model.Crud;
import com.example.escola.Model.Curso;

public class DetailCourse extends AppCompatActivity {

    private EditText name;
    private EditText workload;
    private Crud db;
    private Curso course;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        Intent it = getIntent();
        bundle = it.getExtras();

        name = (EditText) findViewById(R.id.edtName);
        workload = (EditText) findViewById(R.id.edtWorkload);

        // Verifica se existe dado
        // busca o objeto no banco atraves do id
        if(bundle != null){
            db = new Crud(this);
            course = db.selectOneCourse(bundle.getInt("id"));

            name.setText(course.getNome());
            workload.setText(String.valueOf(course.getQtdHoras()));
        }

    }

    // Botão salvar
    public void btnSave(View v){
        if(bundle != null){
            course.setNome(name.getText().toString());
            course.setQtdHoras(Integer.parseInt(workload.getText().toString()));

            db.updateCourse(course);
            sucess();
        }
    }

    // Botão Deletar
    public void btnRemove(View v){
        if(bundle != null){
            AlertDialog.Builder  builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.alertWarning)
                    .setMessage(R.string.fullDelete)
                    .setCancelable(true)
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Caso confirme a exclusão
                            removeConfirm();

                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    // Botão cancelar
    public void btnCancel(View v){
        returnApp();
    }

    // Mensagem de confirmação
    public void sucess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertSave)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // Método responsável para excluir da tabela
    public void removeConfirm(){
        db.rmCourse(course);
        returnApp();
    }

    public void returnApp(){
        Intent it = new Intent(getApplicationContext(), ListCourses.class);
        finish();
        startActivity(it);
    }





}

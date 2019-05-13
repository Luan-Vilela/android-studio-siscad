/*
*   Classe que controla a View que lista todos os
* estudantes cadastrados na tabela estuda.
*
* */
package com.example.escola;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.escola.Model.Crud;
import com.example.escola.Model.CustomDataListStudents;
import com.example.escola.Model.MyAdapterListStudents;
import java.util.ArrayList;

public class ListStudents extends AppCompatActivity{

    private ListView lista;
    Crud db = new Crud(this);
    ArrayList<CustomDataListStudents> listObjectStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        //Botão flutuante adicionar novo Aluno
        FloatingActionButton add = findViewById(R.id.floatBtnAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListStudents.this, NewStudent.class);
                finish();
                startActivity(it);
            }
        });



        lista = findViewById(R.id.lstAluno);

        listedStudents();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Recupera o objeto com base no List e posição clicada ex. objeto[position]
                CustomDataListStudents data = listObjectStudents.get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("id", data.getId());
                Intent it = new Intent(ListStudents.this, DetailStudent.class);
                it.putExtras(bundle);
                finish();
                startActivity(it);

            }
        });
    }

    // Método lista estudantes na listView
    public void listedStudents(){
        listObjectStudents = new ArrayList<>();
        listObjectStudents = db.listaTodosAlunos();
        MyAdapterListStudents adapter = new MyAdapterListStudents(this, listObjectStudents);
        lista.setAdapter(adapter);
    }

}

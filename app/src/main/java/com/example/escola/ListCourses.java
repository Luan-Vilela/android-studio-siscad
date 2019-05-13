/*
 *   Classe que controla a View para lista todos os
 * cursos cadastrados na tabela curso.
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
import com.example.escola.Model.Curso;
import com.example.escola.Model.MyAdapterListCourse;
import java.util.List;

public class ListCourses extends AppCompatActivity {

    private List<Curso> arrCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
        Crud db = new Crud(this);

        ListView lista = (ListView) findViewById(R.id.lstCouse);

        // Botão flutuante para adicionar curso
        FloatingActionButton add = findViewById(R.id.floatBtnAddCourse);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListCourses.this,NewCourses.class);
                finish();
                startActivity(it);
            }
        });

        // Salva todos os cursos cadastrados
        arrCourse = db.listCourses();
        // ListView customizada
        MyAdapterListCourse adapter = new MyAdapterListCourse(this, arrCourse);
        // Seta na listview os cursos
        lista.setAdapter(adapter);

        // Caso click em algum curso
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Recupera o objeto com base no List e posição clicada ex. objeto[position]
                int idCourse = arrCourse.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id", idCourse);

                Intent it = new Intent(ListCourses.this, DetailCourse.class);
                it.putExtras(bundle);
                finish();
                startActivity(it);
            }
        });







    }
}

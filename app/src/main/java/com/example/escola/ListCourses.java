package com.example.escola;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.escola.Model.Crud;
import com.example.escola.Model.Curso;
import com.example.escola.Model.MyAdapterListCourse;

import java.util.ArrayList;
import java.util.List;

public class ListCourses extends AppCompatActivity {

    private List<Curso> arrCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
        Crud db = new Crud(this);

        ListView lista = (ListView) findViewById(R.id.lstCouse);

        FloatingActionButton add = findViewById(R.id.floatBtnAddCourse);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListCourses.this,NewCourses.class);
                finish();
                startActivity(it);
            }
        });

        arrCourse = db.listCourses();

        MyAdapterListCourse adapter = new MyAdapterListCourse(this, arrCourse);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

package com.example.escola;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private Button aluno;
    private Button curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aluno = findViewById(R.id.btnStudent);
        curso = findViewById(R.id.btnCourse);


        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Main.this, ListStudents.class);
                startActivity(it);
            }
        });

        curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Main.this, ListCourses.class);
                startActivity(it);
            }
        });

    }


    public void btnSobre(View v){
        Toast.makeText(this,R.string.developer,Toast.LENGTH_LONG).show();
    }

    public void btnSair(View v){
        finish();
        System.exit(0);
    }
}

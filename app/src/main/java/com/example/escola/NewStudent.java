package com.example.escola;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.escola.Model.Aluno;
import com.example.escola.Model.Crud;
import com.example.escola.Model.Curso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewStudent extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText phone;
    private Spinner course;
    List<Curso> arrCourse;
    Crud db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        nome = (EditText) findViewById(R.id.edtName);
        email = (EditText) findViewById(R.id.edtEmail);
        phone = (EditText) findViewById(R.id.edtPhone);

        db = new Crud(this);

        arrCourse = db.listCourses();
        List<String> nameCourse = new ArrayList<String>();

        for(Curso curso : arrCourse)
            nameCourse.add(curso.getNome());

        if(arrCourse.isEmpty()){
            alertCourse();
        }
        else {
            course = (Spinner) findViewById(R.id.spnCurso);
            ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, nameCourse);
            arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            course.setAdapter(arr);
        }
    }

    public void alertCourse(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertWarning)
                .setMessage(R.string.erroNotCourse)
                .setCancelable(false)
                .setPositiveButton("Novo Curso", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(getApplicationContext(), NewCourses.class);
                        finish();
                        startActivity(it);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



    public void btnSave(View v){
        if(nome.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.errorName,Toast.LENGTH_SHORT).show();
        }
        else if(email.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorEmail,Toast.LENGTH_SHORT).show();
        }
        else if(phone.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.errorPhone, Toast.LENGTH_SHORT).show();

        }
        //criar teste course
        else{
            int idCourse = 0;
            for(Curso curso: arrCourse)
                if(curso.getNome().equals(course.getSelectedItem().toString()))
                    idCourse = curso.getId();

            db.addAluno(new Aluno(nome.getText().toString(), email.getText().toString(), phone.getText().toString(), idCourse));
            sucess();
        }
    }

    public void btnCancel(View v){
        Intent it = new Intent(this, ListStudents.class);
        finish();
        startActivity(it);
    }

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

}


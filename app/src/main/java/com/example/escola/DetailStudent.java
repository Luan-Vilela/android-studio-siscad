/*
* Classe que controla a View Atualizar, Excluir estudantes
*
*/
package com.example.escola;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.escola.Model.Aluno;
import com.example.escola.Model.Crud;
import com.example.escola.Model.Curso;
import java.util.ArrayList;
import java.util.List;

public class DetailStudent extends AppCompatActivity {

    private String id;
    private EditText name;
    private EditText email;
    private EditText phone;
    private Spinner course;
    private Crud db = new Crud(this);
    private List<Curso> arrCourse;
    private Aluno student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);

        Intent it = getIntent();

       Bundle bundle = it.getExtras();



        name = (EditText) findViewById(R.id.edtName);
        email = (EditText) findViewById(R.id.edtEmail);
        phone = (EditText) findViewById(R.id.edtPhone);
        course = (Spinner) findViewById(R.id.spnCurso);

        if(bundle != null)
            loadData(bundle.getInt("id"));

    }

    public void loadData(int id){
        student = db.selecionaAluno(id);
        arrCourse = db.listCourses();



        name.setText(student.getNome());
        email.setText(student.getEmail());
        phone.setText(student.getTelefone());

        List<String> nameCourse = new ArrayList<String>();
        for(Curso curso : arrCourse)
            nameCourse.add(curso.getNome());

        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, nameCourse);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(arr);
        // Seta o curso atual do aluno no spinner.
        // E posCourse corrigi o bug caso um curso anterior tenha sido deletado.
        int posCourse = (db.positionCursoToSpinner(student.getCurso()));
        course.setSelection(posCourse);

    }


    public void btnRemove(View v){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alertWarning)
                .setMessage(R.string.delete)
                .setCancelable(true)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.removeAluno(student);
                        returnApp();
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
    // Botão salvar
    public void btnSaveUpdate(View v){
        // Verifica se existe curso na tabela curso
        int idCourse = 0;
        for(Curso curso: arrCourse)
            if(curso.getNome().equals(course.getSelectedItem().toString()))
                idCourse = curso.getId();

        student.setNome(name.getText().toString());
        student.setEmail(email.getText().toString());
        student.setTelefone(phone.getText().toString());
        student.setCurso(idCourse);


        db.atualizarAluno(student);
        sucess();
    }


    // Alerta de confirmação de salvamento
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

    // Botão cancela
    public void btnCancel(View v){
        returnApp();
    }


    public void returnApp(){
        Intent it = new Intent(getApplicationContext(), ListStudents.class);
        finish();
        startActivity(it);
    }



}

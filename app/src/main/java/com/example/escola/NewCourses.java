package com.example.escola;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escola.Model.Crud;
import com.example.escola.Model.Curso;

public class NewCourses extends AppCompatActivity {

    private EditText name;
    private EditText workload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        name = (EditText) findViewById(R.id.edtName);
        workload = (EditText) findViewById(R.id.edtWorkload);
    }



    public void btnSave(View v){
        if(name.getText().toString().isEmpty())
            Toast.makeText(this,R.string.errorName,Toast.LENGTH_SHORT).show();
        else if(workload.getText().toString().isEmpty())
            Toast.makeText(this, R.string.errorWorkload, Toast.LENGTH_SHORT).show();
        else{
            int conversion = Integer.parseInt(workload.getText().toString());
            Curso course = new Curso(name.getText().toString(), conversion);

            Crud db = new Crud(this);
            db.addCourse(course);
            msgAlert();
        }
    }


    public void btnCancel(View v){
        Intent it = new Intent(this, ListCourses.class);
        finish();
        startActivity(it);
    }


    public void msgAlert(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
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

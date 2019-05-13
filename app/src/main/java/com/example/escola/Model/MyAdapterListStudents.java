/*
* Classe responsável por gerenciar a view que alimenta o
* ListView do aluno
* */
package com.example.escola.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.escola.R;

import java.util.ArrayList;

public class MyAdapterListStudents extends BaseAdapter {

    Context context;
    ArrayList<CustomDataListStudents> arr;


    public MyAdapterListStudents(Context context, ArrayList<CustomDataListStudents> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Mapeá os objetos da view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student,parent,false);

        TextView txtCod = (TextView) convertView.findViewById(R.id.txtCod);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtCourse = (TextView) convertView.findViewById(R.id.txtCourse);

        // Set data into textview
        txtCod.setText(String.valueOf(arr.get(position).getId()));
        txtName.setText(arr.get(position).getNameStudent());
        txtCourse.setText(arr.get(position).getNameCourse());

        return convertView;
    }
}

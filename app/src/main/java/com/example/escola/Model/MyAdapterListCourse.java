/*
* Classe responsavel por gerenciar a View que alimenta
* o ListView do curso.
* */

package com.example.escola.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.escola.Model.Curso;
import com.example.escola.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterListCourse extends BaseAdapter {

    Context context;
    List<Curso> arr;

    public MyAdapterListCourse(Context context, List<Curso> arr) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_list_course, parent,false);

        TextView id = (TextView) convertView.findViewById(R.id.lstIdCourse);
        TextView name = (TextView) convertView.findViewById(R.id.lstNameCourse);
        TextView workload = (TextView) convertView.findViewById(R.id.lstWordload);

        id.setText(String.valueOf(arr.get(position).getId()));
        name.setText(String.valueOf(arr.get(position).getNome()));
        workload.setText(String.valueOf(arr.get(position).getQtdHoras()));

        return convertView;
    }
}

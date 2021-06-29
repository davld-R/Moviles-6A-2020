package com.david.estudentsdata.ui.gallery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.david.estudentsdata.adapters.StudentAdapter;
import com.david.estudentsdata.MainActivity;
import com.david.estudentsdata.R;
import com.david.estudentsdata.models.Student;
import com.david.estudentsdata.utili.DBM;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    public TextView textView;
    public RecyclerView rv;
    public List<Student> list;
    public StudentAdapter adapter;

    Context context;
    DBM dbm;
    String data;
    View root;
    Button btn_modi;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_gallery, container, false);

        lounchData();
        lounchWidgets();
        lounchEvents();

        return root;
    }

    public void lounchWidgets() {
        //textView = root.findViewById(R.id.text_gallery);
        //textView.setText(data);

        rv = root.findViewById(R.id.students_rv);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);

        adapter = new StudentAdapter(context, list, this);

        rv.setAdapter(adapter);
        Log.d("Data", data);
    }



    public void lounchEvents() {

    }

    public void lounchData() {
        context = root.getContext();
        dbm = new DBM(context);
        list = dbm.listStudents();
        data = "";
        //ArrayList<Student> l = dbm.listStudents();
        for (int i = 0; i < list.size(); i++) {
            data += "\n\t***\tDatos del estudiante " + (i + 1) + "\t***\n";
            data += list.get(i).toString();
        }
    }




}
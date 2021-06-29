package com.david.estudentsdata.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.david.estudentsdata.R;
import com.david.estudentsdata.models.Student;
import com.david.estudentsdata.utili.DBM;

public class SlideshowFragment extends Fragment {

    Button btnUA;
    Button btnDA;
    Button btnUO;
    Button btnDO;
    EditText etUO;
    EditText etDO;

    Context context;
    DBM dbm;

    View root;

    Student s;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        lounchWidgets();
        lounchEvents();

        return root;
    }

    public void lounchWidgets() {

        context = root.getContext();

        dbm = new DBM(context);

        btnUA = root.findViewById(R.id.btn_ua);
        btnDA = root.findViewById(R.id.btn_da);
        btnUO = root.findViewById(R.id.btn_uo);
        btnDO = root.findViewById(R.id.btn_do);
        etUO = root.findViewById(R.id.et_uo);
        etDO = root.findViewById(R.id.et_do);

    }

    public void lounchEvents() {

        btnUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ret = dbm.updateAll("?");
                if (ret) {
                    Toast.makeText(context, "Se han actualizado todos los códigos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ret = dbm.deleteAll();

                if (ret){
                    Toast.makeText(context, "Se han borrado todos los registros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cc = Integer.parseInt(etUO.getText().toString());
                s = new Student();
                s.setId(cc);
                s.setName("Estudiante Actualizado");
                boolean ret = dbm.updateStudent(s);
                if (ret){
                    Toast.makeText(context, "Se ha actualizado el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cc = Integer.parseInt(etDO.getText().toString());
                s = new Student();
                s.setId(cc);
                boolean ret = dbm.deleteStudentById(s);
                if (ret){
                    Toast.makeText(context, "Se ha borrado el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
package com.david.example.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.david.example.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {

    //**
    //**
    View view;
    Context context;

    Button btnSimpleDialog;
    Button btnCustomDialog;

    public GalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //**
        //**
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        context = view.getContext();

        lounchWidgets();
        lounchEvents();

        return view;
    }

    public void lounchWidgets() {

        btnSimpleDialog = view.findViewById(R.id.btn_simple_dialog);
        btnCustomDialog = view.findViewById(R.id.btn_custom_dialog);
    }

    public void lounchEvents() {

        btnSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Dialogos Ventanas
                //**
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Este es mi primer dialogo")
                        .setTitle("Primer Dialogo")
                        .setCancelable(false)
                        .setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Boton si activado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Boton No activado", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();


            }
        });

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = getLayoutInflater();

                View vw = inflater.inflate(R.layout.dialog_layout, null);
                final EditText et_in_cd = vw.findViewById(R.id.et_id_cd);

                builder.setView(vw)
                        .setTitle("Dialogo Personalizado")
                        .setMessage("Este es mi dialogo con su propio layout")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, et_in_cd.getText().toString(), Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });

    }
}
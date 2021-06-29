package com.david.homework1.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.david.homework1.Activity.MainMenu;
import com.david.homework1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogsFragment extends Fragment {

    View view;
    Context context;

    Button btndialog;

    public DialogsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DialogsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogsFragment newInstance(String param1, String param2) {
        DialogsFragment fragment = new DialogsFragment();
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
        view = inflater.inflate(R.layout.fragment_dialogs, container, false);
        context = view.getContext();

        launchWidgets();
        launchEvents();

        return view;
    }

    public void launchWidgets() {

        btndialog = view.findViewById(R.id.btn_dialog);

    }

    public void launchEvents() {

        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = getLayoutInflater();

                View viw = inflater.inflate(R.layout.dialog_p, null);
                final EditText et_dialog = viw.findViewById(R.id.et_dialog);



                builder.setView(viw)
                        .setTitle("Dialogo Personalizado")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, et_dialog.getText().toString(), Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Gracias", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
            }
        });

    }
}
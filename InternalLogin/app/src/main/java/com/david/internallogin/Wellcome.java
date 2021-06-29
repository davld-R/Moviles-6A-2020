package com.david.internallogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Wellcome extends AppCompatActivity {
    Context context;
    SharedPreferences sharedPref;
    FloatingActionButton fab;
    String tcl, tvt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        sharedPref = context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        String usr = sharedPref.getString("n_usr", "-");
        String bnu = sharedPref.getString("hola", "Bienvenido Usuario");
        tcl = sharedPref.getString("texCol", "#D5C115");
        tvt = sharedPref.getString("vetCol", "#FFFDF0");

        TextView tv = findViewById(R.id.tv_w);
        tv.setText("" + bnu);

        int t = tv.length();

        if (t > 1) {
            tv.setTextColor(Color.parseColor(tcl));
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(tvt));
        }

        fab = findViewById(R.id.fab);
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, Login.class);
                startActivity(intent);
                return false;
            }
        });
    }

    public void goToLogin() {

    }
}
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

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPref;
    String usr;
    String pwd;
    String tcl, tvt, tbc;
    TextView t1, t2;
    Button btc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        sharedPref = context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);

        usr = sharedPref.getString("n_usr", "-");
        pwd = sharedPref.getString("pwd_usr", "-");
        tcl = sharedPref.getString("texCol", "#D5C115");
        tvt = sharedPref.getString("vetCol", "#FFFDF0");
        tbc = sharedPref.getString("btnCol", "#000000");

        t1 = findViewById(R.id.tv_w);
        t2 = findViewById(R.id.tv_w2);
        btc = findViewById(R.id.bt_co);

        int t = t1.length();

        if (t > 1) {
            t1.setTextColor(Color.parseColor(tcl));
            t2.setTextColor(Color.parseColor(tcl));
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(tvt));
            btc.setBackgroundColor(Color.parseColor(tbc));
        }

    }

    public void login(View v) {
        EditText etU = findViewById(R.id.et_usr);
        EditText etP = findViewById(R.id.et_pwd);

        String u = etU.getText().toString();
        String p = etP.getText().toString();

        if (u.compareTo(usr) == 0 && p.compareTo(pwd) == 0) {

            Intent intent = new Intent(context, Setting.class);
            startActivity(intent);
        } else {
            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}
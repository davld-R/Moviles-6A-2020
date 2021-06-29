package com.david.mypet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


public class Login extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPref;
    String usr, pwd;
    EditText et_reg, et_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        sharedPref = context.getSharedPreferences("File", context.MODE_PRIVATE);

        usr = sharedPref.getString("n_usr", "-");
        pwd = sharedPref.getString("pwd_usr", "-");

        et_reg = findViewById(R.id.et_reg);
        et_con = findViewById(R.id.et_con);




    }

    public void start(View v) {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    public void exit(View v) {
        finish();
    }

    public void next(View v) {
        Intent intent = new Intent(context, Register.class);
        startActivity(intent);
    }

    public void login(View v) {

        String u = et_reg.getText().toString();
        String p = et_con.getText().toString();


        if(u.compareTo(usr) == 0 && p.compareTo(pwd) == 0) {

            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}

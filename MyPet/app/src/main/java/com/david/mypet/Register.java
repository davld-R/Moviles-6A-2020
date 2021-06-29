package com.david.mypet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.david.mypet.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {


    Context context;
    EditText et_reg, et_con;
    String usr, pwd;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        sharedPref = context.getSharedPreferences("File", context.MODE_PRIVATE);

        usr = sharedPref.getString("n_usr", "-");
        pwd = sharedPref.getString("pwd_usr", "-");

        et_reg = findViewById(R.id.et_reg);
        et_con = findViewById(R.id.et_con);

    }

    public void back(View v){
        Intent intent = new Intent(context, Login.class);
        startActivity(intent);
    }

    public  void SaveData(View v){

        String usr = et_reg.getText().toString().trim();
        String pwd = et_con.getText().toString().trim();

        int usrL = usr.length();
        int pwdL = pwd.length();

        if(usrL > 4) {
            if(pwdL > 4){

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("n_usr", usr);
                editor.putString("pwd_usr", pwd);
                editor.commit();
                Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
            } else {
                Snackbar.make(v, "La contraseña debe tener mínimo 4 digitos", Snackbar.LENGTH_SHORT)
                        .show();
            }
        } else {
            Snackbar.make(v, "El usuario debe tener mínimo 4 caracteres", Snackbar.LENGTH_SHORT)
                    .show();
        }

    }

    }


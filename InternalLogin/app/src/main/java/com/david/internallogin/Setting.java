package com.david.internallogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class Setting extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPref;
    String usr;
    Button btc, btc2, btc3, btc4, btc5, btc6, btc7, btc8, btc9, btc10;
    TextView tv, tcol, tcol2, tcol3, tcol4;
    EditText e1;
    String bnu, tcl, tvt, tbc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        sharedPref = context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        usr = sharedPref.getString("n_usr", "-");
        bnu = sharedPref.getString("hola", "Bienvenido Usuario");
        tcl = sharedPref.getString("texCol", "#D5C115");
        tvt = sharedPref.getString("vetCol", "#FFFDF0");
        tbc = sharedPref.getString("btnCol", "#000000");

        tv = findViewById(R.id.tv_w);
        e1 = findViewById(R.id.ed_w);
        tcol = findViewById(R.id.tv_w);
        tcol2 = findViewById(R.id.tv_w2);
        tcol3 = findViewById(R.id.tv_w3);
        tcol4 = findViewById(R.id.tv_w4);
        btc = findViewById(R.id.bt_co);
        btc2 = findViewById(R.id.bt_co2);
        btc3 = findViewById(R.id.bt_co3);
        btc4 = findViewById(R.id.bt_co4);
        btc5 = findViewById(R.id.bt_co5);
        btc6 = findViewById(R.id.bt_co6);
        btc7 = findViewById(R.id.bt_co7);
        btc8 = findViewById(R.id.bt_co8);
        btc9 = findViewById(R.id.bt_co9);
        btc10 = findViewById(R.id.bt_co10);

        int t = tcol.length();

        if (t > 1) {
            tcol.setTextColor(Color.parseColor(tcl));
            tcol2.setTextColor(Color.parseColor(tcl));
            tcol3.setTextColor(Color.parseColor(tcl));
            tcol4.setTextColor(Color.parseColor(tcl));
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(tvt));
            btc.setBackgroundColor(Color.parseColor(tbc));
            btc2.setBackgroundColor(Color.parseColor(tbc));
            btc3.setBackgroundColor(Color.parseColor(tbc));
            btc4.setBackgroundColor(Color.parseColor(tbc));
            btc5.setBackgroundColor(Color.parseColor(tbc));
            btc6.setBackgroundColor(Color.parseColor(tbc));
            btc7.setBackgroundColor(Color.parseColor(tbc));
            btc8.setBackgroundColor(Color.parseColor(tbc));
            btc9.setBackgroundColor(Color.parseColor(tbc));
            btc10.setBackgroundColor(Color.parseColor(tbc));
        }

    }

    public void text(View v) {

        String st = e1.getText().toString();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("hola", st);
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();

    }

    public void textBlack(View v) {

        tcol.setTextColor(Color.parseColor("#2B2A26"));
        tcol2.setTextColor(Color.parseColor("#2B2A26"));
        tcol3.setTextColor(Color.parseColor("#2B2A26"));
        tcol4.setTextColor(Color.parseColor("#2B2A26"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("texCol", "#2B2A26");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();

    }

    public void textRed(View v) {

        tcol.setTextColor(Color.parseColor("#FF0000"));
        tcol2.setTextColor(Color.parseColor("#FF0000"));
        tcol3.setTextColor(Color.parseColor("#FF0000"));
        tcol4.setTextColor(Color.parseColor("#FF0000"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("texCol", "#FF0000");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void textBlue(View v) {

        tcol.setTextColor(Color.parseColor("#1736E3"));
        tcol2.setTextColor(Color.parseColor("#1736E3"));
        tcol3.setTextColor(Color.parseColor("#1736E3"));
        tcol4.setTextColor(Color.parseColor("#1736E3"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("texCol", "#1736E3");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void viewYellow(View v) {

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F3D067"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("vetCol", "#F3D067");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();

    }

    public void viewBlue(View v) {

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#69CBF8"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("vetCol", "#69CBF8");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void viewRed(View v) {

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E87971"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("vetCol", "#E87971");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void btnYellow(View v) {

        btc.setBackgroundColor(Color.parseColor("#FBE734"));
        btc2.setBackgroundColor(Color.parseColor("#FBE734"));
        btc3.setBackgroundColor(Color.parseColor("#FBE734"));
        btc4.setBackgroundColor(Color.parseColor("#FBE734"));
        btc5.setBackgroundColor(Color.parseColor("#FBE734"));
        btc6.setBackgroundColor(Color.parseColor("#FBE734"));
        btc7.setBackgroundColor(Color.parseColor("#FBE734"));
        btc8.setBackgroundColor(Color.parseColor("#FBE734"));
        btc9.setBackgroundColor(Color.parseColor("#FBE734"));
        btc10.setBackgroundColor(Color.parseColor("#FBE734"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("btnCol", "#FBE734");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void btnBlue(View v) {

        btc.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc2.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc3.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc4.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc5.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc6.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc7.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc8.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc9.setBackgroundColor(Color.parseColor("#15A8EA"));
        btc10.setBackgroundColor(Color.parseColor("#15A8EA"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("btnCol", "#15A8EA");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void btnGreen(View v) {

        btc.setBackgroundColor(Color.parseColor("#75D507"));
        btc2.setBackgroundColor(Color.parseColor("#75D507"));
        btc3.setBackgroundColor(Color.parseColor("#75D507"));
        btc4.setBackgroundColor(Color.parseColor("#75D507"));
        btc5.setBackgroundColor(Color.parseColor("#75D507"));
        btc6.setBackgroundColor(Color.parseColor("#75D507"));
        btc7.setBackgroundColor(Color.parseColor("#75D507"));
        btc8.setBackgroundColor(Color.parseColor("#75D507"));
        btc9.setBackgroundColor(Color.parseColor("#75D507"));
        btc10.setBackgroundColor(Color.parseColor("#75D507"));

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("btnCol", "#75D507");
        Toast.makeText(context, "Datos guardados, cierre y abra nuevamente la app", Toast.LENGTH_LONG).show();
        editor.commit();
    }
}
package com.david.homework1.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.david.homework1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    EditText etName;
    EditText etSurname;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lounchWidgets();
        lounchEvents();

    }

    private void lounchWidgets() {

        context = this;

        fab = findViewById(R.id.fab);
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);

    }

    private void lounchEvents() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = etName.getText().toString();
                String p = etSurname.getText().toString();
                Intent i = new Intent(context, MainMenu.class);

                if (u.compareTo("Juan") == 0 && p.compareTo("Rojas") == 0) {
                    i.putExtra("Name", "Juan Rojas");
                    i.putExtra("PicName", R.drawable.admin);
                    startActivity(i);
                    finish();
                } else if (u.compareTo("Pedro") == 0 && p.compareTo("Perez") == 0) {
                    i.putExtra("Name", "Pedro Perez");
                    i.putExtra("PicName", R.drawable.usuario);
                    startActivity(i);
                    finish();
                } else {
                    etName.setText("");
                    etSurname.setText("");
                    Toast.makeText(view.getContext(), " Datos no válidos Envío\nNam: " + u + " Apell: " + p, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
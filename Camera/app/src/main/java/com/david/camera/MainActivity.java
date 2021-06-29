package com.david.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    Activity activity;

    final int MY_PERMISSIONS_REQUEST_CAMERA = 1000;
    final int OPEN_CAM = 1;
    final int OPEN_CAM_2 = 2;

    ImageView ivD;
    ImageView ivI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        activity = this;

        ivD = findViewById(R.id.iv_derecho);
        ivI = findViewById(R.id.iv_izquierdo);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Pida Permisos en la cámara

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Toast.makeText(context, "Se abre la cámara desde OPEN_CAM \n Boton Derecho", Toast.LENGTH_LONG).show();
                    startActivityForResult(camIntent, OPEN_CAM);

                    //Verifica el dialogo de los permisos
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                    Toast.makeText(context, "No se han asignado permisos", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);

                } else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }

            }
        });

        FloatingActionButton fab_2 = findViewById(R.id.fab_2);
        fab_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Pida Permisos en la cámara

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Toast.makeText(context, "Se abre la cámara desde OPEN_CAM_2 \n Boton izquierdo", Toast.LENGTH_LONG).show();
                    startActivityForResult(camIntent, OPEN_CAM_2);

                    //Verifica el dialogo de los permisos
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                    Toast.makeText(context, "No se han asignado permisos", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);

                } else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        Bitmap bmp;

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case OPEN_CAM:
                    Toast.makeText(context, "Se interpreta la respuesta desde OPEN_CAM \n Boton Derecho", Toast.LENGTH_LONG).show();

                    bmp = extras.getParcelable("data");
                    ivD.setImageBitmap(bmp);
                    break;
                case OPEN_CAM_2:
                    Toast.makeText(context, "Se interpreta la respuesta desde OPEN_CAM_2 \n Boton izquierdo", Toast.LENGTH_LONG).show();

                    bmp = extras.getParcelable("data");
                    ivI.setImageBitmap(bmp);
                    break;
                default:
                    Toast.makeText(context, "No se pudo interpretar la respuesta", Toast.LENGTH_LONG).show();
            }
            /**
             if (requestCode == OPEN_CAM) {
             Toast.makeText(context, "Se interpreta la respuesta desde OPEN_CAM \n Boton Derecho", Toast.LENGTH_LONG).show();
             } else if (requestCode == OPEN_CAM_2) {
             Toast.makeText(context, "Se interpreta la respuesta desde OPEN_CAM_2 \n Boton izquierdo", Toast.LENGTH_LONG).show();
             }
             */
        }
    }
}
package com.david.flig;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.david.flig.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                String path = Environment.getStorageDirectory().getAbsolutePath() + File.separator + "MyFolder" + File.separator + "myfilepa";
                Log.d("FLPA", File.separator);
                Log.d("FLPA", path);
                File imgFile = new File(path);
                try {

                    imgFile.createNewFile();
                    imgFile.mkdir();
                    FileOutputStream out = new FileOutputStream(imgFile);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (imgFile.exists()) {

                    Log.d("FLPA", imgFile.getAbsolutePath());

                }

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
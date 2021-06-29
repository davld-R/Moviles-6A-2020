package com.david.homework1.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.david.homework1.Fragments.AnimationFragment;
import com.david.homework1.Fragments.ComponentFragment;
import com.david.homework1.Fragments.DialogsFragment;
import com.david.homework1.Fragments.Test;
import com.david.homework1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    Context context;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        TextView tvName = navigationView.getHeaderView(0).findViewById(R.id.UserName);
        ImageView tvImage = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        Bundle b = getIntent().getExtras();
        String name = b.getString("Name");
        int PicName = b.getInt("PicName");
        tvName.setText(name);
        tvImage.setImageDrawable(getDrawable(PicName));

        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /*
        @Override

        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            int id_menu = item.getItemId();

            if (id_menu == R.id.action_settings) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                finish();
            } else if (id_menu == R.id.action_exit) {
                finish();
            }

            return super.onOptionsItemSelected(item);
        }
       */
    @Override
    public boolean onSupportNavigateUp() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id_menu = item.getItemId();
        Fragment fragment = null;
        String title = "Menú Principal";

        switch (id_menu) {

            case R.id.nav_home:
                fragment = new ComponentFragment();
                title = "Menú de Componentes";
                break;
            case R.id.nav_gallery:
                fragment = new DialogsFragment();
                title = "Menú de Dialogos";
                break;
            case R.id.nav_slideshow:
                fragment = new AnimationFragment();
                title = "Menu de Animaciones";
                break;
            case R.id.nav_test:
                fragment = new Test();
                title = "Menú Evaluación";
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();
            getSupportActionBar().setTitle(title);
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }
}
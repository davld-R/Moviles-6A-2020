package com.david.example.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.david.example.Fragments.GalleryFragment;
import com.david.example.Fragments.HomeFragment;
import com.david.example.Fragments.SlideShowFragment;
import com.david.example.R;
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
    //****
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
        //Este carga la vista
        //****
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Tiene que implementar el listener
        //
        //Aqui esta el menú
        navigationView.setNavigationItemSelectedListener(this);

        //Cambiar nombre de usuario
        TextView tvUN = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        //Cambiar imagen de usuario
        ImageView iv = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        Bundle b = getIntent().getExtras();
        String user = b.getString("UserName");
        int Userpic = b.getInt("UserPic");
        tvUN.setText(user);
        iv.setImageDrawable(getDrawable(Userpic));
        //String pass = b.getString("Pass");

        Toast.makeText(this, " Recibo Usr: " + user, Toast.LENGTH_SHORT).show();
        //Para que la barra lateral inicie abierta
        //****
        drawer.openDrawer(GravityCompat.START);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Esto me falta, las obciones de la derecha
    // primero se escribe onOp
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id_menu = item.getItemId();

        if (id_menu == R.id.action_settings) {
            Toast.makeText(context, "Hola desde el menú settings", Toast.LENGTH_LONG).show();
        } else if (id_menu == R.id.action_say_hi) {
            Toast.makeText(context, "Hola desde el menú Decir Hola", Toast.LENGTH_LONG).show();
        } else if (id_menu == R.id.action_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return false;
    }

    // Esto me falta, las obciones de la izquierda
    // implementar el lisener en la super clase con los implements
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id_menu = item.getItemId();
        Fragment fragment = null;
        String title = "Menú principal";
        switch (id_menu) {

            case R.id.nav_home:
                fragment = new HomeFragment();
                title = "Menú Home";
                break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                title = "Menú Galeria";
                break;
            case R.id.nav_slideshow:
                fragment = new SlideShowFragment();
                title = "Menú Slide Show";
                break;
            case R.id.nav_exit:
                finish();
                break;
            case R.id.nav_logout:
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }

        if(fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();
            getSupportActionBar().setTitle(title);
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

}
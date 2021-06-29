package com.david.mypet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.david.mypet.Fragments.GalleryFragment;
import com.david.mypet.Fragments.HomeFragment;
import com.david.mypet.Fragments.HouseFragment;
import com.david.mypet.Fragments.SlideshowFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    String usr;
    Context context;
    SharedPreferences sharedPref;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        drawer = findViewById(R.id.drawer_layout);

        sharedPref = context.getSharedPreferences("File", context.MODE_PRIVATE);

        usr = sharedPref.getString("n_usr", "-");

        NavigationView navigationView = findViewById(R.id.nav_view);

       // navigationView.setNavigationItemSelectedListener(this);

        TextView tv = navigationView.getHeaderView(0).findViewById(R.id.us_nav);
        tv.setText("" + usr);

     //   drawer.openDrawer(GravityCompat.START);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id_menu = item.getItemId();
        Fragment fragment = null;
        String title = "Menu Principal";

        switch (id_menu) {

            case R.id.nav_home:
                fragment = new HomeFragment();
                title = "Menu Home";
                        break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                title = "Menu Galeria";
                break;
            case R.id.nav_slideshow:
                fragment = new SlideshowFragment();
                title = "Menu Slide";
                break;
            case R.id.nav_house:
                fragment = new HouseFragment();
                title = "Menu House";
                        break;

        }

        if(fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment, fragment);
            ft.commit();
            getSupportActionBar().setTitle(title);
            drawer.closeDrawer(GravityCompat.START);
        }

        return  true;
    }




}
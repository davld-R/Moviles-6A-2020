package com.david.mapacentrado;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.david.mapacentrado.commons.ImageTools;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Context context;
    public Activity activity;
    final int MY_PERMISSIONS_REQUEST_CAMERA = 1000;
    final int OPEN_CAM = 1;
    ImageView iv;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        context = this;
        activity = this;

        iv = findViewById(R.id.pp_bg);
        //btn = findViewById(R.id.btn);
          /*btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                                        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(camIntent, OPEN_CAM);

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
                            });*/


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        askPermissions();
        organizeMarkers();
        lounchEvents();


    }

    public void organizeMarkers() {

        LatLng ubi = new LatLng(1.214702, -77.278222);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubi, 15));
    }

    public void askPermissions() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(context, "Se necesitan permisos", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
    }

    public void askpermissioncamera() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camIntent, OPEN_CAM);

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


    public void lounchEvents() {

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng ltlg) {


                final LatLng latLng = ltlg;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog, null);
                final EditText et = view.findViewById(R.id.et_name);

                builder.setView(view)
                        .setPositiveButton("Registrar", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int i) {


                                boolean banName = et.getText().toString().compareTo("") != 0;
                                int logoImg = R.drawable.usuario2;


                                if (banName) {


                                    View mk = inflater.inflate(R.layout.foto, null);

                                    TextView ppTv = (TextView) mk.findViewById(R.id.pp_tv);
                                    ppTv.setText(et.getText().toString());

                                    ImageView ppIv = (ImageView) mk.findViewById(R.id.pp_bg);
                                    ppIv.setImageDrawable(getDrawable(logoImg));

                                    askpermissioncamera();


                                    mMap.addMarker(new MarkerOptions().position(
                                            new LatLng(latLng.latitude, latLng.longitude))
                                            .title(et.getText().toString())
                                            .icon(
                                                    BitmapDescriptorFactory.fromBitmap(
                                                            ImageTools.createDrawableFromView(context, mk)))
                                    );

                                    dialog.cancel();

                                } else {
                                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                builder.setCancelable(false);
                builder.show();

            }
        });
       /* Este es el Metodo que me sirve para la ubicación se mantenga centrada
            if (mMap != null) {

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    final LatLng Na2 = new LatLng(latitude, longitude);

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Na2, 18));

                }
            });
        }*/
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        Bitmap bmp;

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case OPEN_CAM:
                   // Toast.makeText(context, "Se interpreta la respuesta desde OPEN_CAM \n Boton Derecho", Toast.LENGTH_LONG).show();

                    bmp = extras.getParcelable("data");
                    iv.setImageBitmap(bmp);
                    BitmapDescriptorFactory.fromBitmap(
                            ImageTools.createDrawableFromView(context, iv)
                    );
                    break;
                default:
                  //  Toast.makeText(context, "No se pudo interpretar la respuesta", Toast.LENGTH_LONG).show();
            }
        }
    }*/

}
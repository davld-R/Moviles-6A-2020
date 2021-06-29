package com.david.maps;

import androidx.appcompat.app.AppCompatActivity;

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
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.david.maps.commons.ImageTools;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    public Context context;
    public Activity activity;
    int counter;


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
        counter = 0;


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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /**Cambiar el tipo de mapa**/
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        /**Sckrepchat**/
        mMap.setBuildingsEnabled(true);
        mMap.setOnMarkerClickListener(this);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        //Mover la camara
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        askPermissions();
     //   askPermissions2();
        organizeMarkers();
        lounchEvents();
        drawPolyline();


    }

    public void organizeMarkers() {
        /** Agregando un punto en el mapa en la plaza de Nariño**/
        LatLng plN = new LatLng(1.214702, -77.278222);
        MarkerOptions marker = new MarkerOptions().position(plN).title("Plaza de Nariño Mia");
        mMap.addMarker(marker);

        LatLng mm = new LatLng(1.214702, -77.278222);
        MarkerOptions marker3 = new MarkerOptions().position(mm).title("Marcador Movil");
        marker3.draggable(true);
        marker3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(marker3);

        LatLng plC = new LatLng(1.210760, -77.276629);
        MarkerOptions marker2 = new MarkerOptions().position(plC).title("Plaza del Carnaval Mia");
        mMap.addMarker(marker2);


        //mMap.moveCamera(CameraUpdateFactory.newLatLng(plN));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plN, 15));

    }

    public void askPermissions() {
         /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "No se han asignado permisos", Toast.LENGTH_LONG).show();
                return;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
            }
        }
        */

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(context, "No se han asignado permisos", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    10);
        }
    }

    public void lounchEvents() {
        if (mMap != null) {


            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(location.getLatitude(),
                                    location.getLongitude()), 15));

                   /* mMap.addMarker(new MarkerOptions().position(
                            new LatLng(location.getLatitude(), location.getLongitude())
                            )
                                    .title("Mi ubicacion")
                    );*/
                }
            });

            mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
                @Override
                public void onPoiClick(PointOfInterest pointOfInterest) {
                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(pointOfInterest.latLng.latitude, pointOfInterest.latLng.latitude)
                            )
                                    .title("Punto de Interes")
                    );
                }
            });

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng ltlg) {

                    final LatLng latLng = ltlg;

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    final LayoutInflater inflater = getLayoutInflater();

                    View v = inflater.inflate(R.layout.dialog, null);
                    final EditText et = v.findViewById(R.id.et_name);
                    final Spinner sp = v.findViewById(R.id.sp_col);

                    builder.setView(v)
                            .setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    boolean banName = et.getText().toString().compareTo("") != 0;
                                    String col = sp.getSelectedItem().toString();
                                    int logoImg = R.drawable.ccp;
                                    if (col.compareTo("CCP") == 0) {
                                        logoImg = R.drawable.ccp;
                                    } else if (col.compareTo("INEM") == 0) {
                                        logoImg = R.drawable.inem;
                                    } else {
                                        logoImg = R.drawable.itsim;
                                    }
                                    //boolean banCol = sp.getSelectedItem().toString().compareTo("")!= 0;
                                    if (banName) {
                                        View mkV = inflater.inflate(R.layout.marker, null);

                                        TextView ppTV = (TextView) mkV.findViewById(R.id.pp_tv);
                                        ppTV.setText(et.getText().toString());

                                        ImageView ppIV = (ImageView) mkV.findViewById(R.id.pp_bg);
                                        ppIV.setImageDrawable(getDrawable(logoImg));


                                       // askPermissions2();


                                        mMap.addMarker(new MarkerOptions().position(
                                                new LatLng(latLng.latitude, latLng.longitude)
                                                )
                                                        .title(et.getText().toString())
                                                        .icon(
                                                                BitmapDescriptorFactory.fromBitmap(
                                                                        ImageTools.createDrawableFromView(context, mkV)))
                                        );

                                        dialog.cancel();
                                    } else {
                                        Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    builder.setCancelable(false);
                    builder.show();



                   /* counter++;

                    // Cargando la vista del push pin
                    // Se infla la vista, se trae el marker o la vista que cree
                    LayoutInflater inflater = getLayoutInflater();
                    View mkV = inflater.inflate(R.layout.marker, null);

                    TextView ppTV = (TextView) mkV.findViewById(R.id.pp_tv);
                    ppTV.setText("Este es el clic # " + counter);

                    ImageView ppIV = (ImageView) mkV.findViewById(R.id.pp_bg);
                    ppIV.setImageDrawable(getDrawable(counter % 2 == 0 ? R.drawable.logo : R.drawable.escudo));

                    mMap.addMarker(new MarkerOptions().position(
                            new LatLng(latLng.latitude, latLng.longitude)
                            )
                                    .title("Punto del evento")
                                    .icon(
                                            BitmapDescriptorFactory.fromBitmap(
                                                    ImageTools.createDrawableFromView(context, mkV)))
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo))
                            //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                    );*/
                }
            });
        }
    }

    public void drawPolyline() {
        LatLng pi = new LatLng(1.1981147100978513, -77.27786265296739);
        LatLng p1 = new LatLng(1.2105798490623547, -77.27648338814683);
        LatLng p2 = new LatLng(1.2104206450025918, -77.28260278860037);
        LatLng pf = new LatLng(1.212779, -77.287188);

        List<PatternItem> pattern = Arrays.<PatternItem>asList(new Dash(30), new Gap(20));


        Polyline pl = mMap.addPolyline(new PolylineOptions().add(pi, p1, p2, pf).width(3).color(Color.BLUE));
        pl.setPattern(pattern);

    }



    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(context, "Información de "+marker.getTitle(), Toast.LENGTH_SHORT).show();

        return false;
    }
}
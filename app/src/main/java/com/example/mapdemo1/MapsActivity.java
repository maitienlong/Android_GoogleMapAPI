package com.example.mapdemo1;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mapdemo1.markDAO.MapDAO;
import com.example.mapdemo1.model.Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText edtNameLocation;
    EditText edtLat;
    EditText edtLng;
    MapDAO mapDAO;
    Marker mm, ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        edtNameLocation = findViewById(R.id.edtNameLocation);
        edtLat = findViewById(R.id.edtLat);
        edtLng = findViewById(R.id.edtLng);
        mapDAO = new MapDAO(MapsActivity.this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final List<Map> mapList = mapDAO.getAllMark();
        String dName = "";
        double dLat = 0;
        double dLng = 0;
        for (int i = 0; i < mapList.size(); i++) {
            dName = mapList.get(i).name;
            dLat += mapList.get(i).lat;
            dLng += mapList.get(i).lng;

        }

        LatLng sydney = new LatLng(dLat, dLng);

        mMap.addMarker(new MarkerOptions().position(sydney).title(dName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                List<Map> OnClickMapList = mapDAO.getNameMark("'" + marker.getTitle() + "'");
                String eName = "";
                double eLat = 0;
                double eLng = 0;

                for (int e = 0; e < OnClickMapList.size(); e++) {
                    eName = OnClickMapList.get(e).name;
                    eLat += OnClickMapList.get(e).lat;
                    eLng += OnClickMapList.get(e).lng;
                }

                edtNameLocation.setText(eName);
                edtLat.setText(eLat + "");
                edtLng.setText(eLng + "");

                return false;
            }
        });


    }

    public void btnThemMarker(View view) {

        try {
            if (edtNameLocation.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Tên vị trí không được bỏ trống !", Toast.LENGTH_SHORT).show();
                edtNameLocation.requestFocus();
                return;
            } else if (edtLat.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Latitude không được bỏ trống !", Toast.LENGTH_SHORT).show();
                edtLat.requestFocus();
                return;
            } else if (edtLng.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Longitude không được bỏ trống !", Toast.LENGTH_SHORT).show();
                edtLng.requestFocus();
                return;
            } else {

                List<Map> mapList = new ArrayList<>();
                Map map = new Map();
                map.name = edtNameLocation.getText().toString().trim();
                map.lat = Double.parseDouble(edtLat.getText().toString().trim());
                map.lng = Double.parseDouble(edtLng.getText().toString().trim());
                mapList.add(map);

                long result = mapDAO.insertMark(map);
                if (result > 0) {
                    Toast.makeText(MapsActivity.this, "Bạn đã thêm thành công một vị trí", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MapsActivity.this, "Không thể thêm vị trí", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
        }

        final List<Map> mapList = mapDAO.getNameMark("'" + edtNameLocation.getText().toString().trim() + "'");
        String dName = "";
        double dLat = 0;
        double dLng = 0;
        for (int i = 0; i < mapList.size(); i++) {
            dName = mapList.get(i).name;
            dLat += mapList.get(i).lat;
            dLng += mapList.get(i).lng;

        }

        LatLng vta = new LatLng(dLat, dLng);
        mm = mMap.addMarker(new MarkerOptions().position(vta).title(dName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vta));

    }

    public void btnSuaMarker(View view) {

        try {
            if (edtNameLocation.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Tên vị trí không được bỏ trống !", Toast.LENGTH_SHORT).show();
                edtNameLocation.requestFocus();
                return;
            } else if (edtLat.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Latitude không được bỏ trống !", Toast.LENGTH_SHORT).show();
                edtLat.requestFocus();
                return;
            } else if (edtLng.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Longitude không được bỏ trống !", Toast.LENGTH_SHORT).show();
                edtLng.requestFocus();
                return;
            } else {

                List<Map> mapList = new ArrayList<>();
                Map map = new Map();
                map.name = edtNameLocation.getText().toString().trim();
                map.lat = Double.parseDouble(edtLat.getText().toString().trim());
                map.lng = Double.parseDouble(edtLng.getText().toString().trim());
                mapList.add(map);

                long result = mapDAO.updateMark(map);
                if (result > 0) {
                    Toast.makeText(MapsActivity.this, "Bạn đã chỉnh sửa thành công một vị trí", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MapsActivity.this, "Không chỉnh sửa tên vị trí", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
        }

        final List<Map> mapList = mapDAO.getNameMark("'" + edtNameLocation.getText().toString().trim() + "'");
        String dName = "";
        double dLat = 0;
        double dLng = 0;
        for (int i = 0; i < mapList.size(); i++) {
            dName = mapList.get(i).name;
            dLat += mapList.get(i).lat;
            dLng += mapList.get(i).lng;

        }

        LatLng sydney = new LatLng(dLat, dLng);
        if (ma != null){
            ma.remove();
        }

        ma = mMap.addMarker(new MarkerOptions().position(sydney).title(dName));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mm.remove();
    }

    public void btnXoaMarker(View view) {
    }
}

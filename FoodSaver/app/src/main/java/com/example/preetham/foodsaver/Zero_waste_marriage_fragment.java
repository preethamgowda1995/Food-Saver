package com.example.preetham.foodsaver;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

/**
 * Created by preetham on 4/8/2018.
 */

public class Zero_waste_marriage_fragment extends Fragment implements LocationListener {
    LocationManager locationManager;
    DatabaseReference databaseReference;
    EditText title,donatedate,servefor,cookedat,sustaintills,address;
    String selectedItemText1,selectedItemText2;
    Button add,retiveloc;
    Spinner type_of_food,time_of_food;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myview=inflater.inflate(R.layout.zero_waste_marriage_fragment,container,false);
        title=(EditText)myview.findViewById(R.id.event_name);
        donatedate=(EditText)myview.findViewById(R.id.member_count);
        servefor=(EditText)myview.findViewById(R.id.event_date);
        cookedat=(EditText)myview.findViewById(R.id.event_time);
        sustaintills=(EditText)myview.findViewById(R.id.contact);
        address=(EditText)myview.findViewById(R.id.address);
        add=(Button)myview.findViewById(R.id.add_event);
        retiveloc=(Button)myview.findViewById(R.id.retrive_location);


        type_of_food=(Spinner)myview.findViewById(R.id.food_type);
        time_of_food=(Spinner)myview.findViewById(R.id.food_time);

        ArrayAdapter<String> myadapter1=new ArrayAdapter<String>(myview.getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.food_type));
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_of_food.setAdapter(myadapter1);

        type_of_food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedItemText1 = (String) parent.getItemAtPosition(position);
                //title.setText(selectedItemText1.toString());
                // Notify the selected item text
                //Toast.makeText
                //(myview.getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                //.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(myview.getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.food_time));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_of_food.setAdapter(myadapter);

        time_of_food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 selectedItemText2 = (String) parent.getItemAtPosition(position);
                //title.setText(selectedItemText2.toString());
                // Notify the selected item text
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        retiveloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("zero_waste");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(title.getText().toString()) || TextUtils.isEmpty(servefor.getText().toString()) || TextUtils.isEmpty(cookedat.getText().toString()) || TextUtils.isEmpty(sustaintills.getText().toString()) || TextUtils.isEmpty(address.getText().toString()) || TextUtils.isEmpty(donatedate.getText().toString()))
                {
                    if (TextUtils.isEmpty(title.getText().toString())) {
                        title.setError("title cannot be empty");
                        title.requestFocus();
                    }
                    if (TextUtils.isEmpty(servefor.getText().toString())) {
                        servefor.setError("this field is mandatory");
                        servefor.requestFocus();
                    }

                    if (TextUtils.isEmpty(cookedat.getText().toString())) {
                        servefor.setError("this field is mandatory");
                        servefor.requestFocus();
                    }
                    if (TextUtils.isEmpty(sustaintills.getText().toString())) {
                        servefor.setError("this field is mandatory");
                        servefor.requestFocus();
                    }
                    if (TextUtils.isEmpty(donatedate.getText().toString())) {
                        servefor.setError("this field is mandatory");
                        servefor.requestFocus();
                    }
                    if (TextUtils.isEmpty(address.getText().toString())) {
                        servefor.setError("this field is mandatory");
                        servefor.requestFocus();
                    }
                }
                else {

                    final String title1 = title.getText().toString().trim();
                    String donatef = donatedate.getText().toString().trim();
                    String servef = servefor.getText().toString().trim();
                    String cookedf = cookedat.getText().toString().trim();
                    String sustainf = sustaintills.getText().toString().trim();
                    String addressf = address.getText().toString().trim();


                    final zero_waste add = new zero_waste(title1, donatef, servef, cookedf, sustainf, addressf, selectedItemText1, selectedItemText2);
                    //databaseReference.push().setValue(addproduct);

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(String.valueOf(title1)).exists()) {
                                // run some code
                                Toast.makeText(myview.getContext(), "Detils Already Added", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child(String.valueOf(title1)).setValue(add);

                                Toast.makeText(myview.getContext(), "Details Added successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getActivity(), HomeActivity.class);
                                startActivity(i);
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //access permission for maps
                    if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                    }

                }}
        });
        return myview;
    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address.setText(address.getText() + "\n"+addresses.get(0).getAddressLine(0));
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getActivity(), "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }
}

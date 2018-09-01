package com.example.preetham.foodsaver;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
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
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

public class Donate_food_fragment extends Fragment implements LocationListener{

    LocationManager locationManager;
    DatabaseReference databaseReference;
    EditText title,donatedate,servefor,cookedat,sustaintills,address;
    Spinner type_of_food;
    Button add,retiveloc;
    String selectedItemText;
    ActionBar actionBar;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myview = inflater.inflate(R.layout.donate_food_fragment, container, false);


        type_of_food = (Spinner) myview.findViewById(R.id.food_type);

        final ArrayAdapter<String> myadapter = new ArrayAdapter<String>(myview.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.food_type));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_of_food.setAdapter(myadapter);

        type_of_food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);

                //set what ever you want to any component


                //title.setText(selectedItemText.toString());
                // Notify the selected item text

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Accepting values from the EditText

        title = (EditText) myview.findViewById(R.id.titled);
        donatedate = (EditText) myview.findViewById(R.id.donation_date);
        servefor = (EditText) myview.findViewById(R.id.servefor);
        cookedat = (EditText) myview.findViewById(R.id.cooked_at);
        sustaintills = (EditText) myview.findViewById(R.id.sustaintill);
        address = (EditText) myview.findViewById(R.id.address);
        add = (Button) myview.findViewById(R.id.submit);
        retiveloc = (Button) myview.findViewById(R.id.retrive_location);
        retiveloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Donate_food");
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
                //String myaddp = selectedItemText.toString().trim();




                final donatefood add = new donatefood(title1, donatef, servef, cookedf, sustainf, addressf, selectedItemText);
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

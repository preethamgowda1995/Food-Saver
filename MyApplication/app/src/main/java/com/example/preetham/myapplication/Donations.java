package com.example.preetham.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Donations extends Fragment {

    Button money_donar,food_donar,donatefood,foodsaver,zero_waste;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myview=inflater.inflate(R.layout.donations,container,false);

        money_donar=(Button)myview.findViewById(R.id.ngo_activity);
        food_donar=(Button)myview.findViewById(R.id.ngo_event);
        donatefood=(Button)myview.findViewById(R.id.food_donars);
        foodsaver=(Button)myview.findViewById(R.id.food_saver);
        zero_waste=(Button)myview.findViewById(R.id.zero_waste);

        money_donar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),Money_Donars_Activity.class);
                startActivity(i);
            }
        });

        food_donar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),ngoEvent.class);
                startActivity(i);
            }
        });

        donatefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),Food_Donars_Activity.class);
                startActivity(i);
            }
        });
        foodsaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),foodsaver.class);
                startActivity(i);
            }
        });

        zero_waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),zerowaste.class);
                startActivity(i);
            }
        });


        return myview;}
}

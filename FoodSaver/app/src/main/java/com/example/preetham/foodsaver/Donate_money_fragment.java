package com.example.preetham.foodsaver;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by preetham on 4/8/2018.
 */

public class Donate_money_fragment extends Fragment {

    Button donate_for_ngo,donate_for_event;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View myview=inflater.inflate(R.layout.donate_money_fragment,container,false);

        donate_for_ngo=(Button)myview.findViewById(R.id.donate_ngo);
        donate_for_event=(Button)myview.findViewById(R.id.donate_event);

        donate_for_ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),Donate_NGO_Activity.class);
                startActivity(i);
            }
        });

        donate_for_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(myview.getContext(),Donate_Event_Activity.class);
                startActivity(i);
            }
        });

        return myview;
    }
}

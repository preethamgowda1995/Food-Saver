package com.example.preetham.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Money_Donars_Activity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    Activity content;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money__donars_);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));


        String[] items = {};
        itemList = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);
        ListView listV = (ListView) findViewById(R.id.list_item);
        listV.setAdapter(adapter);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child("ngo_activity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    //Loop 1 to go through all the child nodes of users
                    String bookskey = uniqueKeySnapshot.getKey();
                    String name = uniqueKeySnapshot.child("name").getValue().toString();
                    String phno = uniqueKeySnapshot.child("amount").getValue().toString();

                    DataSnapshot uniqueKey;
                    for (DataSnapshot booksSnapshot : uniqueKeySnapshot.getChildren()) {
                        //loop 2 to go through all the child nodes of books node
                        // String bookskey = booksSnapshot.getKey();
                        //Toast.makeText(Main2Activity.this, bookskey, Toast.LENGTH_SHORT).show();
                        //itemList.add(bookskey);
                        // notify listview of data changed
                        //sadapter.notifyDataSetChanged();
                        //String booksValue = booksSnapshot.getValue();
                    }
                    itemList.add(name + "\n" + phno);

                    // notify listview of data changed
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

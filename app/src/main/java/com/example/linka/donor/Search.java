package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    Spinner state,district,city;
    String st,dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        state=findViewById(R.id.state);
        district=findViewById(R.id.district);
        city=findViewById(R.id.city);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation = findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home: {
                        Intent in = new Intent(Search.this, MainPage.class);
                        startActivity(in);
                        break;
                    }case R.id.search:{
                        Intent in= new Intent(Search.this, Search.class);
                        startActivity(in);break;
                    }case R.id.history:{
                        Intent in=new Intent(Search.this, History.class);
                        startActivity(in);break;
                    }case R.id.transfer:{
                        Intent in= new Intent(Search.this, Transfer.class);
                        startActivity(in);break;
                    }case R.id.withdraw:{
                        Intent in= new Intent(Search.this, Withdraw.class);
                        startActivity(in);break;
                    }case R.id.logout:{
                        FirebaseAuth.getInstance().signOut();
                        Intent in= new Intent(Search.this, Login.class);
                        startActivity(in);break;
                    }}
                return false;
            }
        });
        databaseReference.child("blood_bank").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final List<String> stateN= new ArrayList<String>();
                final List<String> distN= new ArrayList<String>();
                final List<String> cityN= new ArrayList<String>();
                for(DataSnapshot bankSnapshot : dataSnapshot.getChildren()){
                    String stateName = bankSnapshot.child("state_l").getValue(String.class);
                    if(stateN.contains(stateName)){

                    }else{
                        stateN.add(stateName);
                    }
                }
                ArrayAdapter<String> stateAdapter= new ArrayAdapter<String>(Search.this,R.layout.support_simple_spinner_dropdown_item, stateN);
                stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                state.setAdapter(stateAdapter);
                st= state.getSelectedItem().toString();
                district.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(DataSnapshot bankSnapshot : dataSnapshot.getChildren()){
                            String distName = bankSnapshot.child("dist_l").getValue(String.class);
                            String stName = bankSnapshot.child("state_l").getValue(String.class);
                            if(distN.contains(distName)){

                            }else{
                                if(stName==st){
                                    distN.add(distName);
                                }
                            }
                        }
                        ArrayAdapter<String> distAdapter= new ArrayAdapter<String>(Search.this,R.layout.support_simple_spinner_dropdown_item, distN);
                        distAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        district.setAdapter(distAdapter);
                        dt= district.getSelectedItem().toString();
                    }
                });
                city.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(DataSnapshot bankSnapshot : dataSnapshot.getChildren()){
                            String cityName = bankSnapshot.child("city_l").getValue(String.class);
                            String dstName = bankSnapshot.child("dist_l").getValue(String.class);
                            String staName = bankSnapshot.child("state_l").getValue(String.class);
                            if(cityN.contains(cityName)){

                            }else{
                                if((staName==st)&&(dstName==dt)){
                                    cityN.add(cityName);
                                }
                            }
                        }
                        ArrayAdapter<String> cityAdapter= new ArrayAdapter<String>(Search.this,R.layout.support_simple_spinner_dropdown_item, cityN);
                        cityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        city.setAdapter(cityAdapter);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.linka.donor;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

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
    Spinner state,district,city,bd_group;
    Button searchBtn;
    ConstraintLayout parLy;
    private RecordAdapter adapter;
    ListView search_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        state=findViewById(R.id.state);
        district=findViewById(R.id.district);
        city=findViewById(R.id.city);
        bd_group=findViewById(R.id.bd_group);
        searchBtn=findViewById(R.id.searchBtn);
        parLy=findViewById(R.id.parLay);
        search_result=findViewById(R.id.search_result);
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
                List<String> stateN= new ArrayList<>();
                stateN.add("All");
                for(DataSnapshot bankSnapshot : dataSnapshot.getChildren()){
                    String stateName = bankSnapshot.child("state_l").getValue(String.class);
                    if(stateN.contains(stateName)){

                    }else{
                        stateN.add(stateName);
                    }
                }
                ArrayAdapter<String> stateAdapter= new ArrayAdapter<>(Search.this,R.layout.support_simple_spinner_dropdown_item, stateN);
                stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                state.setAdapter(stateAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String st = state.getSelectedItem().toString();
                databaseReference.child("blood_bank").orderByChild("state_l").equalTo(st).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> districtN= new ArrayList<>();
                        districtN.add("All");
                        for(DataSnapshot bankSnapshot: dataSnapshot.getChildren()){
                            String distName=bankSnapshot.child("district_l").getValue(String.class);
                            if(districtN.contains(distName)){

                            }else{
                                districtN.add(distName);
                            }
                        }
                        ArrayAdapter<String> distAdapter= new ArrayAdapter<>(Search.this,R.layout.support_simple_spinner_dropdown_item, districtN);
                        distAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        district.setAdapter(distAdapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dt=district.getSelectedItem().toString();
                databaseReference.child("blood_bank").orderByChild("district_l").equalTo(dt).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> cityN= new ArrayList<>();
                        cityN.add("All");
                        for(DataSnapshot bankSnapshot: dataSnapshot.getChildren()){
                            String distName=bankSnapshot.child("city_l").getValue(String.class);
                            if(cityN.contains(distName)){

                            }else{
                                cityN.add(distName);
                            }
                        }
                        ArrayAdapter<String> cityAdapter= new ArrayAdapter<>(Search.this,R.layout.support_simple_spinner_dropdown_item, cityN);
                        cityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        city.setAdapter(cityAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        bd_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String b_group=bd_group.getSelectedItem().toString();
                final String state_name=state.getSelectedItem().toString();
                final String district_name=state.getSelectedItem().toString();
                final String city_name=state.getSelectedItem().toString();
                if(state_name.equalsIgnoreCase("all")){
                    databaseReference.child("blood_bank").orderByChild(b_group).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot recordSnapshot: dataSnapshot.getChildren()){
                                ArrayList<record> bankN=new ArrayList<>();
                                String ava=recordSnapshot.child(b_group).getValue(String.class);
                                if(ava==null){
                                    continue;
                                }
                                int ava_l=Integer.parseInt(ava);
                                if(ava_l>0){
                                    record rec=new record(recordSnapshot.child("bname").getValue(String.class),recordSnapshot.child("add_l").getValue(String.class),
                                            recordSnapshot.child("city_l").getValue(String.class),recordSnapshot.child("district_l").getValue(String.class),
                                            recordSnapshot.child("state_l").getValue(String.class),ava);
                                    bankN.add(rec);
                                }
                                adapter= new RecordAdapter(Search.this,bankN);
                                search_result.setAdapter(adapter);
                                startActivity(new Intent(Search.this,search_result.class));
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else if(state_name!="All"&&district_name.equalsIgnoreCase("All")){
                    databaseReference.child("blood_bank").orderByChild(b_group).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot recordSnapshot: dataSnapshot.getChildren()){
                                ArrayList<record> bankN=new ArrayList<>();
                                String ava=recordSnapshot.child(b_group).getValue(String.class);
                                String disava=recordSnapshot.child("state_l").getValue(String.class);
                                int ava_l=Integer.parseInt(ava);
                                if((ava_l>0)&&(state_name.equalsIgnoreCase(disava))){
                                    record rec=new record(recordSnapshot.child("bname").getValue(String.class),recordSnapshot.child("add_l").getValue(String.class),
                                            recordSnapshot.child("city_l").getValue(String.class),recordSnapshot.child("district_l").getValue(String.class),
                                            recordSnapshot.child("state_l").getValue(String.class),ava);
                                    bankN.add(rec);
                                }
                                adapter= new RecordAdapter(Search.this,bankN);
                                search_result.setAdapter(adapter);
                                startActivity(new Intent(Search.this,search_result.class));
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else if(district_name!="null"&&city_name.equalsIgnoreCase("All")){
                    databaseReference.child("blood_bank").orderByChild(b_group).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot recordSnapshot: dataSnapshot.getChildren()){
                                ArrayList<record> bankN=new ArrayList<>();
                                String ava=recordSnapshot.child(b_group).getValue(String.class);
                                String stateava=recordSnapshot.child("state_l").getValue(String.class);
                                String distava=recordSnapshot.child("district_l").getValue(String.class);
                                int ava_l=Integer.parseInt(ava);
                                if((ava_l>0)&&(state_name.equalsIgnoreCase(stateava))&&(district_name.equalsIgnoreCase(distava))){
                                    record rec=new record(recordSnapshot.child("bname").getValue(String.class),recordSnapshot.child("add_l").getValue(String.class),
                                            recordSnapshot.child("city_l").getValue(String.class),recordSnapshot.child("district_l").getValue(String.class),
                                            recordSnapshot.child("state_l").getValue(String.class),ava);
                                    bankN.add(rec);
                                    adapter= new RecordAdapter(Search.this,bankN);
                                    search_result.setAdapter(adapter);
                                    startActivity(new Intent(Search.this,search_result.class));
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else if(city_name!="All"){
                    databaseReference.child("blood_bank").orderByChild(b_group).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ArrayList<record> bankN=new ArrayList<>();
                            for(DataSnapshot recordSnapshot: dataSnapshot.getChildren()){
                                String ava=recordSnapshot.child(b_group).getValue(String.class);
                                String stateava=recordSnapshot.child("state_l").getValue(String.class);
                                String distava=recordSnapshot.child("district_l").getValue(String.class);
                                String cityava=recordSnapshot.child("city_l").getValue(String.class);
                                int ava_l=Integer.parseInt(ava);
                                if((ava_l>0)&&(state_name.equalsIgnoreCase(stateava))&&(district_name.equalsIgnoreCase(distava))&&(city_name.equalsIgnoreCase(cityava))){
                                    record rec=new record(recordSnapshot.child("bname").getValue(String.class),recordSnapshot.child("add_l").getValue(String.class),
                                            recordSnapshot.child("city_l").getValue(String.class),recordSnapshot.child("district_l").getValue(String.class),
                                            recordSnapshot.child("state_l").getValue(String.class),ava);
                                    bankN.add(rec);
                                }
                            }
                            adapter= new RecordAdapter(Search.this,bankN);
                            search_result.setAdapter(adapter);
                            startActivity(new Intent(Search.this,search_result.class));
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
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
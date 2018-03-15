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
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    TextView uni_ava,uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        uni_ava=findViewById(R.id.unit_ava);
        uName=findViewById(R.id.uName);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation = findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home: {
                        Intent in = new Intent(MainPage.this, MainPage.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.search: {
                        Intent in = new Intent(MainPage.this, Search.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.history: {
                        Intent in = new Intent(MainPage.this, History.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.transfer: {
                        Intent in = new Intent(MainPage.this, Transfer.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.withdraw: {
                        Intent in = new Intent(MainPage.this, Withdraw.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.logout: {
                        FirebaseAuth.getInstance().signOut();
                        Intent in = new Intent(MainPage.this, Login.class);
                        startActivity(in);
                        break;
                    }
                }
                return false;
            }
        });
        final String id=mAuth.getCurrentUser().getUid();
        databaseReference.child("client").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userf = dataSnapshot.child(id).child("f_name").getValue(String.class);
                String userl = dataSnapshot.child(id).child("l_name").getValue(String.class);
                String units = dataSnapshot.child(id).child("units").getValue(String.class);
                uni_ava.setText(units);
                uName.setText("Welcome\n"+userf+" "+userl);
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

    @Override
    public void onBackPressed() {

    }
}
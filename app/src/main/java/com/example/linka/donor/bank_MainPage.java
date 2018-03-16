package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bank_MainPage extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    TextView bName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank__main_page);
        startDrawer();
    }

    private void startDrawer() {
        mDrawerLayout = findViewById(R.id.drawerLayout2);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation = findViewById(R.id.navigation2);
        mAuth = FirebaseAuth.getInstance();
        final String idN=mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        bName=findViewById(R.id.bName);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.bank_home: {
                        Intent in = new Intent(bank_MainPage.this, bank_MainPage.class);
                        startActivity(in);
                        break;
                    }case R.id.bank_pending:{
                        Intent in= new Intent(bank_MainPage.this, bank_pending.class);
                        startActivity(in);break;
                    }case R.id.bank_start:{
                        Intent in=new Intent(bank_MainPage.this, bank_startDonation.class);
                        startActivity(in);break;
                    }case R.id.bank_dispense:{
                        Intent in= new Intent(bank_MainPage.this, bank_DispenseBlood.class);
                        startActivity(in);break;
                    }case R.id.bank_check:{
                        Intent in= new Intent(bank_MainPage.this, bank_checkInventory.class);
                        startActivity(in);break;
                    }case R.id.logout2:{
                        FirebaseAuth.getInstance().signOut();
                        Intent in= new Intent(bank_MainPage.this, Login.class);
                        startActivity(in);break;
                    }}
                return false;
            }
        });
        databaseReference.child("blood_bank").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bNameU = dataSnapshot.child(idN).child("bname").getValue(String.class);
                bName.setText("Welcome\n"+bNameU);
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

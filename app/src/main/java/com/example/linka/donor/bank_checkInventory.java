package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bank_checkInventory extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    ListView inven_view;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_check_inventory);
        startDrawer();
        mAuth = FirebaseAuth.getInstance();
        final String idN=mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        inven_view=findViewById(R.id.inven_view);
        databaseReference.child("blood_bank").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> inv=new ArrayList<>();
                inv.add("  ");
                inv.add("A+             :      "+dataSnapshot.child(idN).child("a_positive").getValue(String.class));
                inv.add("A-             :      "+dataSnapshot.child(idN).child("a_negative").getValue(String.class));
                inv.add("B+             :      "+dataSnapshot.child(idN).child("b_positive").getValue(String.class));
                inv.add("B-             :      "+dataSnapshot.child(idN).child("b_negative").getValue(String.class));
                inv.add("AB+            :      "+dataSnapshot.child(idN).child("ab_positive").getValue(String.class));
                inv.add("AB-            :      "+dataSnapshot.child(idN).child("ab_negative").getValue(String.class));
                inv.add("O+             :      "+dataSnapshot.child(idN).child("o_positive").getValue(String.class));
                inv.add("O-             :      "+dataSnapshot.child(idN).child("o_negative").getValue(String.class));
                inv.add("Bombay Type    :      "+dataSnapshot.child(idN).child("bombay").getValue(String.class));
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(bank_checkInventory.this,R.layout.list_pop,inv);
                inven_view.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void startDrawer() {
        mDrawerLayout = findViewById(R.id.drawerLayout2);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation = findViewById(R.id.navigation2);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.bank_home: {
                        Intent in = new Intent(bank_checkInventory.this, bank_MainPage.class);
                        startActivity(in);break;
                    }case R.id.bank_pending:{
                        Intent in= new Intent(bank_checkInventory.this, bank_pending.class);
                        startActivity(in);break;
                    }case R.id.bank_start:{
                        Intent in=new Intent(bank_checkInventory.this, bank_startDonation.class);
                        startActivity(in);break;
                    }case R.id.bank_dispense:{
                        Intent in= new Intent(bank_checkInventory.this, bank_DispenseBlood.class);
                        startActivity(in);break;
                    }case R.id.bank_check:{
                        Intent in= new Intent(bank_checkInventory.this, bank_checkInventory.class);
                        startActivity(in);break;
                    }case R.id.logout2:{
                        FirebaseAuth.getInstance().signOut();
                        Intent in= new Intent(bank_checkInventory.this, Login.class);
                        startActivity(in);break;
                    }}
                return false;
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

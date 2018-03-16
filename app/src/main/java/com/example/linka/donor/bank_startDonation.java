package com.example.linka.donor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import net.glxn.qrgen.android.QRCode;

public class bank_startDonation extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    FirebaseAuth mAuth;
    ImageView bank_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_start_donation);
        mAuth = FirebaseAuth.getInstance();
        final String idN=mAuth.getCurrentUser().getUid();
        bank_qr=findViewById(R.id.bank_qr);
        Bitmap qr= QRCode.from(idN).bitmap();
        bank_qr.setImageBitmap(qr);
        startDrawer();
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
                        Intent in = new Intent(bank_startDonation.this, bank_MainPage.class);
                        startActivity(in);
                        break;
                    }case R.id.bank_pending:{
                        Intent in= new Intent(bank_startDonation.this, bank_pending.class);
                        startActivity(in);break;
                    }case R.id.bank_start:{
                        Intent in=new Intent(bank_startDonation.this, bank_startDonation.class);
                        startActivity(in);break;
                    }case R.id.bank_dispense:{
                        Intent in= new Intent(bank_startDonation.this, bank_DispenseBlood.class);
                        startActivity(in);break;
                    }case R.id.bank_check:{
                        Intent in= new Intent(bank_startDonation.this, bank_checkInventory.class);
                        startActivity(in);break;
                    }case R.id.logout2:{
                        FirebaseAuth.getInstance().signOut();
                        Intent in= new Intent(bank_startDonation.this, Login.class);
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

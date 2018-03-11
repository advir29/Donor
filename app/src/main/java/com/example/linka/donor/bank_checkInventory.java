package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class bank_checkInventory extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_check_inventory);
        startDrawer();
    }

    private void startDrawer() {
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
                    case R.id.bank_home: {
                        Intent in = new Intent(bank_checkInventory.this, bank_MainPage.class);
                        startActivity(in);
                        break;
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
                    }case R.id.logout:{
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

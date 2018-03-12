package com.example.linka.donor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class nD extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_d);
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
                        Intent in = new Intent(nD.this, MainPage.class);
                        startActivity(in);
                        break;
                    }case R.id.search:{
                        Intent in= new Intent(nD.this, Search.class);
                        startActivity(in);break;
                    }case R.id.history:{
                        Intent in=new Intent(nD.this, History.class);
                        startActivity(in);break;
                    }case R.id.transfer:{
                        Intent in= new Intent(nD.this, Transfer.class);
                        startActivity(in);break;
                    }case R.id.withdraw:{
                        Intent in= new Intent(nD.this, Withdraw.class);
                        startActivity(in);break;
                    }case R.id.logout:{
                        FirebaseAuth.getInstance().signOut();
                        Intent in= new Intent(nD.this, Login.class);
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

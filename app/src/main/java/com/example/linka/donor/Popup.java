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
import android.widget.Button;

public class Popup extends AppCompatActivity {
    Button nDo, pDo;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        nDo=findViewById(R.id.nD);
        pDo=findViewById(R.id.pD);
        nDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(Popup.this,nD.class);
                startActivity(it);
            }
        });
        pDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(Popup.this, pD.class);
                startActivity(it);
            }
        });
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
                switch (id) {
                    case R.id.home: {
                        Intent in = new Intent(Popup.this, MainPage.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.search: {
                        Intent in = new Intent(Popup.this, Search.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.history: {
                        Intent in = new Intent(Popup.this, History.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.transfer: {
                        Intent in = new Intent(Popup.this, Transfer.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.withdraw: {
                        Intent in = new Intent(Popup.this, Withdraw.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.logout: {
                        Intent in = new Intent(Popup.this, Login.class);
                        startActivity(in);
                        break;
                    }
                }
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

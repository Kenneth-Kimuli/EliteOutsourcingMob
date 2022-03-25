package com.example.eliteoutsourcing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.eliteoutsourcing.Fragment.HomeFragment;
import com.example.eliteoutsourcing.Fragment.MessageFragment;
import com.example.eliteoutsourcing.Fragment.NotificationFragment;
import com.example.eliteoutsourcing.Fragment.PayrollFragment;
import com.example.eliteoutsourcing.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;
    //private Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.bnmhome);
        /*reg = findViewById(R.id.btnHRegister);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));
            }
        });

         */

        // TODO: 15/10/21 Use recyclerviewer instead of listviewer;
        // Just like ListView, Android recyclerview is used to display large amount of items on screen,
        // but with improved performance and other benefits.

    }
    HomeFragment homeFragment = new HomeFragment();
    MessageFragment messageFragment = new MessageFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    PayrollFragment payrollFragment = new PayrollFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bnmhome:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;

            case R.id.bnmmessage:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, messageFragment).commit();
                return true;

            case R.id.bnmnotification:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, notificationFragment).commit();
                return true;

            case R.id.bnmpayroll:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, payrollFragment).commit();
                return true;

            case R.id.bnmprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                return true;
        }
        return false;
    }

}
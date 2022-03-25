package com.example.eliteoutsourcing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button login, register;
    TextView app_name;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // redirect if user is not null
        if(firebaseUser!= null){
            startActivity( new Intent(StartActivity.this,HomeActivity.class) );
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start );

        login = findViewById( R.id.login );
        register = findViewById( R.id.register );
        app_name = findViewById(R.id.tvAppname);

        YoYo.with(Techniques.Pulse)
                .duration(5000) // Time it for app name to fade in up
                .playOn(app_name);

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( StartActivity.this,LoginActivity.class ) );
            }
        } );
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( StartActivity.this,RegistrationActivity.class ) );
            }
        } );
    }

}
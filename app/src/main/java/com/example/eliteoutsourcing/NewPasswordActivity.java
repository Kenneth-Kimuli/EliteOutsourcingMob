package com.example.eliteoutsourcing;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewPasswordActivity extends AppCompatActivity {

    private EditText Newpwd, Confirmpwd;
    private Button Updatepwd;
    private Toolbar toolbar;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        setUpUIViews();
        initializeToolbar();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Updatepwd.setOnClickListener(view -> {
            if (validate()) {
                String Newpwd_str = Newpwd.getText().toString();

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                firebaseUser.updatePassword(Newpwd_str).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(NewPasswordActivity.this, "Password has been changed!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NewPasswordActivity.this, "Password update failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

    private void setUpUIViews() {
        Newpwd = findViewById(R.id.et_Newpwd);
        Confirmpwd = findViewById(R.id.et_NewCpwd);
        Updatepwd = findViewById(R.id.btn_NewPwd);
        toolbar = findViewById(R.id.tb_NewPwd);
    }

    //options drop down
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create a new password");
    }

    private Boolean validate() {
        Boolean validation = false;

        String Newpwd_str = Newpwd.getText().toString();
        String Confirmpwd_str = Confirmpwd.getText().toString();


        if (Newpwd_str.isEmpty()) {
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
        } else if (Confirmpwd_str.isEmpty()) {
            Toast.makeText(this, "Confirm your Password", Toast.LENGTH_SHORT).show();
        } else if (!Newpwd_str.equals(Confirmpwd_str)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        } else {
            validation = true;
        }

        return validation;
    }
}
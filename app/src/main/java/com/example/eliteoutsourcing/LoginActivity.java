package com.example.eliteoutsourcing;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import static com.airbnb.lottie.L.TAG;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button LogIn;
    private TextView SignUp, ForgotPassword;
    private FirebaseAuth mAuth;
    //since verification may take a while, we can display a message to user
    //private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpUIViews();
//        checkCurrentUser();
        mAuth = FirebaseAuth.getInstance();





        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate();

                String emailstr = Email.getText().toString().trim();
                String passwordstr = Password.getText().toString().trim();
                
                if (emailstr.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                } else if (passwordstr.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Invalid Password: 6 characters", Toast.LENGTH_SHORT).show();
                }
                else {


                    mAuth.signInWithEmailAndPassword(emailstr, passwordstr)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        Toast.makeText(LoginActivity.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    } else {
                                        //Toast.makeText(this, "Verify your email",Toast.LENGTH_SHORT).show();
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();

                                    }
                                }
                            });
                }
                }

        });

        /*
         * Sign Out
         * AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });

         */

        /*
         * Delete account
         * AuthUI.getInstance()
        .delete(this)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // ...
            }
        });



        * Terms and conditions
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                        "https://example.com/terms.html",
                        "https://example.com/privacy.html")
                .build();
        signInLauncher.launch(signInIntent);

         */


      /*  // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);


       */

        /*checkCurrentUser();


        progressDialog = new ProgressDialog(this);


        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                //checkEmailVerification();

            }
        });

         */

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, NewPasswordActivity.class));
            }
        });
    }



    //When initializing your Activity, check to see if the user is currently signed in.
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void validate() {
        String emailstr = Email.getText().toString().trim();
        String passwordstr = Password.getText().toString().trim();

        //message to user on what's going on; clue
        //progressDialog.setMessage("This might take a while...");
        //progressDialog.show();

        if (emailstr.isEmpty()) {
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
        } else if (passwordstr.isEmpty()) {
            Toast.makeText(this, "Invalid Password: 6 characters", Toast.LENGTH_SHORT).show();
        }
        else {

            /* mAuth.signInWithEmailAndPassword(emailstr, passwordstr).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        checkEmailVerification();
                        progressDialog.dismiss();
                        //startActivity(new Intent(LogIn.this, Home.class));

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "LogIn Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

             */

            mAuth.signInWithEmailAndPassword(emailstr, passwordstr)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getInstance().getCurrentUser();
                                Boolean emailverify = user.isEmailVerified();

                                if (emailverify) {
                                    finish();
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Toast.makeText(LoginActivity.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                } else {
                                    //Toast.makeText(this, "Verify your email",Toast.LENGTH_SHORT).show();
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                    mAuth.signOut();

                                }
                            }
                        }
                    });
        }   }


   /* private void checkEmailVerification(){
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        Boolean emailverify = user.isEmailVerified();

        if (emailverify){
            finish();
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "signInWithEmail:success");
            //FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
            Toast.makeText(LoginActivity.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }else {
            //Toast.makeText(this, "Verify your email",Toast.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            updateUI(null);
            mAuth.signOut();
        }


    }

    */



    private void checkCurrentUser(){
        //object of main class,
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        //checks with db if user has already logged in, must direct him to next activity without asking him to login again
        if (firebaseUser != null){
            finish();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
    }


    private void setUpUIViews(){
        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        LogIn = (Button)findViewById(R.id.btnLogIn);
        SignUp = (TextView)findViewById(R.id.tvSignUp);
        ForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
}
package com.example.eliteoutsourcing;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RegistrationActivity extends AppCompatActivity {

    private ImageView UserPic;
    private EditText FirName, SurName, EmailAdd, Password, CPassword;
    private TextView Login;
    private Toolbar toolbar;
    String fnamestr, snamestr, emailstr, pwdstr, cpwdstr;
    private Button BtnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef, databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setUpUIViews();
        initializeToolbar();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // To kick off the FirebaseUI sign in flow, create a sign in intent with your preferred sign-in methods:
        // Choose authentication providers
        // ActionCodeSettings; Structure that contains the required continue/state URL with optional Android and iOS bundle identifiers.
        // The stateUrl used to initialize this class is the link/deep link/fallback url used while constructing the Firebase dynamic link
        // continue URL; The continue URL ('http://myurl.io/join') is the final redirect link which would be embedded in the continueUrl query parameter of the link.
        // You would typically after email verification is complete, show a continue button to go to that link. This can be passed from the client and must be a whitelisted domain.




        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    createUserAccount();
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    private void setUpUIViews(){
        UserPic = findViewById(R.id.image_profile);
        FirName = findViewById(R.id.etFirstName);
        SurName = findViewById(R.id.etSurName);
        EmailAdd = findViewById(R.id.etEmailAddress);
        Password = findViewById(R.id.etPassword);
        CPassword = findViewById(R.id.etConfirmPassword);
        BtnRegister = findViewById(R.id.btnRegister);
        Login = findViewById(R.id.tvRegLogin);
        toolbar = findViewById(R.id.tb_Reg);
    }

    private void createUserAccount(){


        //upload to database, trim removes the whitespaces
        String emailstr = EmailAdd.getText().toString().trim();
        String pwdstr = Password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(emailstr, pwdstr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            sendEmailVerification();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private Boolean validate(){
        Boolean validation = false;

        fnamestr = FirName.getText().toString();
        snamestr = SurName.getText().toString();
        emailstr = EmailAdd.getText().toString();
        pwdstr = Password.getText().toString();
        cpwdstr = CPassword.getText().toString();


        if(fnamestr.isEmpty()){
            Toast.makeText(this, "Please enter your First Name", Toast.LENGTH_SHORT).show();
        }else if(snamestr.isEmpty()){
            Toast.makeText(this, "Please enter your Surname", Toast.LENGTH_SHORT).show();
        }else if(emailstr.isEmpty()){
            Toast.makeText(this, "Please enter an Email Address", Toast.LENGTH_SHORT).show();
        }else if (pwdstr.isEmpty()){
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_SHORT).show();
        }else if (cpwdstr.isEmpty()){
            Toast.makeText(this, "Confirm your Password", Toast.LENGTH_SHORT).show();
        }else if (!pwdstr.equals(cpwdstr)){
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }else{
            validation = true;
        }

        return validation;
    }

    private void sendUserData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        myRef = databaseReference.child("Users").child(mAuth.getUid());
        //StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("VendorProfile pic"); //like creating a folder for each user, the file being uploaded and the name of the file (User_id/Images/Profile_pic.png)
        //UploadTask uploadTask = imageReference.putFile(imagePath);
        //uploadTask.addOnFailureListener(new OnFailureListener() {
        //  @Override
        //public void onFailure(@NonNull Exception e) {
        //Toast.makeText(SignUp.this, "Image upload failed!",Toast.LENGTH_SHORT).show();
        //}
        //}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        // @Override
        //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        //imagePath = taskSnapshot.getDownloadUrl();
        //  Toast.makeText(SignUp.this, "Image upload successful!",Toast.LENGTH_SHORT).show();
        //}
        //});
        VendorProfile vendorProfile = new VendorProfile(fnamestr,snamestr,emailstr);
        myRef.setValue(vendorProfile);
    }


    private void sendEmailVerification() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setAndroidPackageName(
                        "com.example.eliteoutsourcing",
                        true,
                        null)
                .setHandleCodeInApp(true) // This must be set to true
                .setUrl("https://eliteoutsourcing.page.link/") // This URL needs to be whitelisted
                .build();

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().enableEmailLinkSignIn()
                        .setActionCodeSettings(actionCodeSettings).build()
                //,new AuthUI.IdpConfig.PhoneBuilder().build()
        );



        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //save data of spam users
                        //sendUserData();
                        Toast.makeText(RegistrationActivity.this, "User registration successful!" +
                                " Verification email sent!", Toast.LENGTH_SHORT).show();
                        updateUI(firebaseUser);
                        mAuth.signOut();
                        finish();
                        //startActivity(new Intent(RegistrationActivity.this, LogIn.class));
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

                    } else if (AuthUI.canHandleIntent(getIntent())) {
                        if (getIntent().getExtras() == null) {
                            return;
                        }
                        String link = getIntent().getExtras().getString(ExtraConstants.EMAIL_LINK_SIGN_IN);
                        if (link != null) {
                            Intent signInIntent = AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setEmailLink(link)
                                    .setAvailableProviders(providers)
                                    .build();
                            signInLauncher.launch(signInIntent);
                        }
                    } else {
                        //when firebase server is down, no internet
                        Toast.makeText(RegistrationActivity.this, "Verification email not sent!", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
    }

    //Create an ActivityResultLauncher which registers a callback for the FirebaseUI Activity result contract:
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );


    //When the sign-in flow is complete, you will receive the result in onSignInResult:
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // ...
        } else {
            Toast.makeText(this, "Sign in failed",Toast.LENGTH_SHORT).show();
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
    /*private void signIn(){
        mAuth.signInWithEmailAndPassword(email, pwdstr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }*/

    private void initializeToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register an account");
    }

    private void reload() { }

    private void updateUI(FirebaseUser firebaseUser) {
        sendUserData();
        /*databaseReference = FirebaseDatabase.getInstance().getReference();
        myRef = databaseReference.child("Users").child(mAuth.getUid());
        //StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("VendorProfile pic"); //like creating a folder for each user, the file being uploaded and the name of the file (User_id/Images/Profile_pic.png)
        //UploadTask uploadTask = imageReference.putFile(imagePath);
        //uploadTask.addOnFailureListener(new OnFailureListener() {
        //  @Override
        //public void onFailure(@NonNull Exception e) {
        //Toast.makeText(SignUp.this, "Image upload failed!",Toast.LENGTH_SHORT).show();
        //}
        //}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        // @Override
        //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        //imagePath = taskSnapshot.getDownloadUrl();
        //  Toast.makeText(SignUp.this, "Image upload successful!",Toast.LENGTH_SHORT).show();
        //}
        //});
        VendorProfile vendorProfile = new VendorProfile(fnamestr,snamestr,emailstr);
        myRef.setValue(vendorProfile);

         */

    }
}
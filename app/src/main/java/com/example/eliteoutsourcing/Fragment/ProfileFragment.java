package com.example.eliteoutsourcing.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eliteoutsourcing.HomeActivity;
import com.example.eliteoutsourcing.R;
import com.example.eliteoutsourcing.StartActivity;
import com.example.eliteoutsourcing.VendorProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {

    public static final String NATIONALITY_KEY = "nationality";
    public static final String PHONE_NUMBER_KEY = "phone number";
    public static final String TAG = "PersonalInformation";
    public static final String USER_DETAILS = "User Details: ";
    //private DocumentReference mDocRef = FirebaseFirestore.getInstance().collection("Applicants").document("PersonalInformtion").collection("PI_1").document("") etc;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Applicants/Users/Personal/Information");
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid());

    Button logout, save;
    FirebaseAuth firebaseAuth;
    EditText nationality, phoneNo, FirName, SurName, EmailAdd;
    String fnamestr, snamestr, emailstr, nationalitystr, phoneNostr, usernamestr;
    TextView Username;

    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public void onStart(){
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VendorProfile vendorProfile = snapshot.getValue(VendorProfile.class);
                fnamestr = vendorProfile.getFname();
                snamestr = vendorProfile.getSname();
                emailstr = vendorProfile.getEmail();
                usernamestr = fnamestr + " " + snamestr;

                FirName.setText(fnamestr);
                SurName.setText(snamestr);
                EmailAdd.setText(emailstr);
                Username.setText(usernamestr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error);
            }
        });

        mDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()) {
                    nationalitystr = documentSnapshot.getString(NATIONALITY_KEY);
                    phoneNostr = documentSnapshot.getString(PHONE_NUMBER_KEY);

                    nationality.setText(nationalitystr);
                    phoneNo.setText(phoneNostr);
            } else if (e != null) {
                Log.w(TAG, "Got an exception!", e);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        setUpUiViews(view);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), StartActivity.class));

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileDetails(view);
                //fetchProfileDetails(view);
            }
        });
        return view;
    }

    //fetches using button
    public void fetchProfileDetails(View view){
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    nationalitystr = documentSnapshot.getString(NATIONALITY_KEY);
                    phoneNostr = documentSnapshot.getString(PHONE_NUMBER_KEY);

                    nationality.setText(nationalitystr);
                    phoneNo.setText(phoneNostr);
                    //To get the whole document
                    //Map<String, Object> myData = documentSnapshot.getData();
                    //just like realtime db, gets data from firebase and creates an object with the data as fields
                    //VendorProfile vendorProfile = documentSnapshot.toObject(VendorProfile.class);
                }
            }
        });
    }

    public void saveProfileNameEmail(View view){

    }
    public void saveProfileDetails(View view){
        nationalitystr = nationality.getText().toString();
        phoneNostr = phoneNo.getText().toString();
        fnamestr = FirName.getText().toString();
        snamestr = SurName.getText().toString();
        emailstr = EmailAdd.getText().toString();

        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("First Name", fnamestr);
        dataToSave.put("Surname", snamestr);
        dataToSave.put("Email Address", emailstr);
        dataToSave.put(NATIONALITY_KEY, nationalitystr);
        dataToSave.put(PHONE_NUMBER_KEY, phoneNostr);

        mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document Saved!");
                Toast.makeText(getActivity(),"Personal Information Updated!",
                        Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Document not Saved!", e);
                Toast.makeText(getActivity(),"Update Failed!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setUpUiViews(View view) {
        logout = view.findViewById(R.id.btnLogout);
        save = view.findViewById(R.id.btnEdit_Save);
        nationality = view.findViewById(R.id.etNationality);
        phoneNo = view.findViewById(R.id.etPhoneNumber);
        FirName = view.findViewById(R.id.etFirstName);
        SurName = view.findViewById(R.id.etSurName);
        EmailAdd = view.findViewById(R.id.etEmailAddress);
        Username = view.findViewById(R.id.tvusername);
    }
}

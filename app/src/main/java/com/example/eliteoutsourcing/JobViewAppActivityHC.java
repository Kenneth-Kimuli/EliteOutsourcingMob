package com.example.eliteoutsourcing;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static android.content.ContentValues.TAG;

public class JobViewAppActivityHC extends AppCompatActivity {

    String titlestr, typestr, industrystr, experiencestr, descriptionstr, salarystr;
    TextView title, type, industry, experience, description, salary;

    private FirebaseFirestore jobsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view_app);

        jobsRef = FirebaseFirestore.getInstance();
        setUpUiViews();


        getListItemsHC();

    }

    public void setUpUiViews(){
        title = findViewById(R.id.tvTitle);
        type = findViewById(R.id.tvType);
        industry = findViewById(R.id.tvIndustry);
        experience = findViewById(R.id.tvExperience);
        description = findViewById(R.id.tvDescription);
        salary = findViewById(R.id.tvSalary);
    }

    public void getAllDocs() {
        /* mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {

                }
            }
        });
*/
    }

    private void getListItemsTech() {
        jobsRef.collection("Jobs")
                .whereEqualTo("Industry", "technology")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            //List<Jobs> jobs = documentSnapshots.toObjects(Jobs.class);

                            // Add all to your list
                            //mArrayList.addAll(jobs);

                            //Log.d(TAG, "onSuccess: " + mArrayList);

                            // Get the last visible document
                            List<DocumentSnapshot> snapshotList = documentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot: snapshotList) {
                                Log.d(TAG, "onSuccess: " + snapshot.getData().toString());

                                titlestr = snapshot.getString("Title");
                                typestr = snapshot.getString("Type");
                                industrystr = snapshot.getString("Industry");
                                experiencestr = snapshot.getString("Experience");
                                descriptionstr = snapshot.getString("Description");
                                salarystr = snapshot.getString("Salary");

                                title.setText(titlestr);
                                type.setText(typestr);
                                industry.setText(industrystr);
                                experience.setText(experiencestr);
                                description.setText(descriptionstr);
                                salary.setText(salarystr);
                            }

                            // Construct a new query starting at this document,
                            // get the next 25 cities.
                            /*Query next = jobsRef.collection("Jobs")
                                    .orderBy("population")
                                    .startAfter(lastVisible)
                                    .limit(25);*/

                            // Use the query for pagination


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getListItemsManu() {
        jobsRef.collection("Jobs")
                .whereEqualTo("Industry", "manufacturing")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            //List<Jobs> jobs = documentSnapshots.toObjects(Jobs.class);

                            // Add all to your list
                            //mArrayList.addAll(jobs);

                            //Log.d(TAG, "onSuccess: " + mArrayList);

                            // Get the last visible document
                            List<DocumentSnapshot> snapshotList = documentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot: snapshotList) {
                                Log.d(TAG, "onSuccess: " + snapshot.getData().toString());

                                titlestr = snapshot.getString("Title");
                                typestr = snapshot.getString("Type");
                                industrystr = snapshot.getString("Industry");
                                experiencestr = snapshot.getString("Experience");
                                descriptionstr = snapshot.getString("Description");
                                salarystr = snapshot.getString("Salary");

                                title.setText(titlestr);
                                type.setText(typestr);
                                industry.setText(industrystr);
                                experience.setText(experiencestr);
                                description.setText(descriptionstr);
                                salary.setText(salarystr);
                            }

                            // Construct a new query starting at this document,
                            // get the next 25 cities.
                            /*Query next = jobsRef.collection("Jobs")
                                    .orderBy("population")
                                    .startAfter(lastVisible)
                                    .limit(25);*/

                            // Use the query for pagination


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getListItemsHAM() {
        jobsRef.collection("Jobs")
                .whereEqualTo("Industry", "hospitality&management")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            //List<Jobs> jobs = documentSnapshots.toObjects(Jobs.class);

                            // Add all to your list
                            //mArrayList.addAll(jobs);

                            //Log.d(TAG, "onSuccess: " + mArrayList);

                            // Get the last visible document
                            List<DocumentSnapshot> snapshotList = documentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot: snapshotList) {
                                Log.d(TAG, "onSuccess: " + snapshot.getData().toString());

                                titlestr = snapshot.getString("Title");
                                typestr = snapshot.getString("Type");
                                industrystr = snapshot.getString("Industry");
                                experiencestr = snapshot.getString("Experience");
                                descriptionstr = snapshot.getString("Description");
                                salarystr = snapshot.getString("Salary");

                                title.setText(titlestr);
                                type.setText(typestr);
                                industry.setText(industrystr);
                                experience.setText(experiencestr);
                                description.setText(descriptionstr);
                                salary.setText(salarystr);
                            }

                            // Construct a new query starting at this document,
                            // get the next 25 cities.
                            /*Query next = jobsRef.collection("Jobs")
                                    .orderBy("population")
                                    .startAfter(lastVisible)
                                    .limit(25);*/

                            // Use the query for pagination


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getListItemsHC() {
        jobsRef.collection("Jobs")
                .whereEqualTo("Industry", "health care")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            //List<Jobs> jobs = documentSnapshots.toObjects(Jobs.class);

                            // Add all to your list
                            //mArrayList.addAll(jobs);

                            //Log.d(TAG, "onSuccess: " + mArrayList);

                            // Get the last visible document
                            List<DocumentSnapshot> snapshotList = documentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot: snapshotList) {
                                Log.d(TAG, "onSuccess: " + snapshot.getData().toString());

                                titlestr = snapshot.getString("Title");
                                typestr = snapshot.getString("Type");
                                industrystr = snapshot.getString("Industry");
                                experiencestr = snapshot.getString("Experience");
                                descriptionstr = snapshot.getString("Description");
                                salarystr = snapshot.getString("Salary");

                                title.setText(titlestr);
                                type.setText(typestr);
                                industry.setText(industrystr);
                                experience.setText(experiencestr);
                                description.setText(descriptionstr);
                                salary.setText(salarystr);
                            }

                            // Construct a new query starting at this document,
                            // get the next 25 cities.
                            /*Query next = jobsRef.collection("Jobs")
                                    .orderBy("population")
                                    .startAfter(lastVisible)
                                    .limit(25);*/

                            // Use the query for pagination


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getListItemsTransport() {
        jobsRef.collection("Jobs")
                .whereEqualTo("Industry", "transport")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            //List<Jobs> jobs = documentSnapshots.toObjects(Jobs.class);

                            // Add all to your list
                            //mArrayList.addAll(jobs);

                            //Log.d(TAG, "onSuccess: " + mArrayList);

                            // Get the last visible document
                            List<DocumentSnapshot> snapshotList = documentSnapshots.getDocuments();
                            for (DocumentSnapshot snapshot: snapshotList) {
                                Log.d(TAG, "onSuccess: " + snapshot.getData().toString());

                                titlestr = snapshot.getString("Title");
                                typestr = snapshot.getString("Type");
                                industrystr = snapshot.getString("Industry");
                                experiencestr = snapshot.getString("Experience");
                                descriptionstr = snapshot.getString("Description");
                                salarystr = snapshot.getString("Salary");

                                title.setText(titlestr);
                                type.setText(typestr);
                                industry.setText(industrystr);
                                experience.setText(experiencestr);
                                description.setText(descriptionstr);
                                salary.setText(salarystr);
                            }

                            // Construct a new query starting at this document,
                            // get the next 25 cities.
                            /*Query next = jobsRef.collection("Jobs")
                                    .orderBy("population")
                                    .startAfter(lastVisible)
                                    .limit(25);*/

                            // Use the query for pagination


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
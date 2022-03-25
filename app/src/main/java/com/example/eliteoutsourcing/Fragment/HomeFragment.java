package com.example.eliteoutsourcing.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.*;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.eliteoutsourcing.JobViewAppActivity;
import com.example.eliteoutsourcing.JobViewAppActivityHAM;
import com.example.eliteoutsourcing.JobViewAppActivityHC;
import com.example.eliteoutsourcing.JobViewAppActivityManu;
import com.example.eliteoutsourcing.JobViewAppActivityTrans;
import com.example.eliteoutsourcing.R;

public class HomeFragment extends Fragment {

    CardView technology, manu, HAM, HC, Trans;
    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setUpUiViews(view);

        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity(view);
            }
        });

        manu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity(view);
            }
        });

        HAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity(view);
            }
        });

        HC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity(view);
            }
        });

        Trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newActivity(view);
            }
        });

        return view;
    }

    public void setUpUiViews(View view) {
        technology = view.findViewById(R.id.Technology_card);
        manu = view.findViewById(R.id.Manufacturing_card);
        HAM = view.findViewById(R.id.hospitality_management_card);
        HC = view.findViewById(R.id.HealthCare_card);
        Trans = view.findViewById(R.id.Transport_card);
    }

    public void setTechnology(CardView technology) {
        this.technology = technology;
    }
    
    public void newActivity (View view){
        startActivity(new Intent(getActivity(), JobViewAppActivity.class));
    }

    public void newActivityManu (View view){
        startActivity(new Intent(getActivity(), JobViewAppActivityManu.class));
    }

    public void newActivityHAM (View view){
        startActivity(new Intent(getActivity(), JobViewAppActivityHAM.class));
    }

    public void newActivityHC (View view){
        startActivity(new Intent(getActivity(), JobViewAppActivityHC.class));
    }

    public void newActivityTrans (View view){
        startActivity(new Intent(getActivity(), JobViewAppActivityTrans.class));
    }

}

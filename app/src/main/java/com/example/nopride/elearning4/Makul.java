package com.example.nopride.elearning4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Makul extends Fragment {

    public Makul(){}
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.activity_matakuliah, container, false);

        getActivity().setTitle("Makul");
        Log.e("Makul", "Makul");

        return view;
    }

}

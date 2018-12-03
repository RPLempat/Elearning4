package com.example.nopride.elearning4;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nopride.elearning4.model.Mahasiswa;
import com.example.nopride.elearning4.model.MahasiswaList;
import com.example.nopride.elearning4.remote.RetrofitClient;
import com.example.nopride.elearning4.remote.UserService;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Root extends Fragment  {

    private ArrayList<Mahasiswa> mahasiswa;

    private RecyclerView recyclerView;
    private MahasiswaAdapter eAdapter;

    public Root(){}
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.root, container, false);

        getActivity().setTitle("Beranda");
        Log.e("Root", "Beranda");


        //Creating an object of our api interface
        UserService api = RetrofitClient.getApiService();

        /**
         * Calling JSON
         */
        Call<MahasiswaList> call = api.getMahasiswa();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<MahasiswaList>() {
            @Override
            public void onResponse(Call<MahasiswaList> call, Response<MahasiswaList> response) {
                //Dismiss Dialog
               // pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    mahasiswa = (ArrayList<Mahasiswa>) response.body().getMahasiswa();
                    recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
                    eAdapter = new MahasiswaAdapter(mahasiswa);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                }
            }

            @Override
            public void onFailure(Call<MahasiswaList> call, Throwable t) {
          //      pDialog.dismiss();
            }
        });
        return rootView;
    }



}

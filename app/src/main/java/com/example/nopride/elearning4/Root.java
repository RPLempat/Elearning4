package com.example.nopride.elearning4;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nopride.elearning4.model.Mahasiswa;
import com.example.nopride.elearning4.model.MahasiswaList;
import com.example.nopride.elearning4.remote.ApiClient;
import com.example.nopride.elearning4.remote.ApiInterface;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Root extends Fragment  {

    private ArrayList<Mahasiswa> mahasiswa;


    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static Navigation ma;

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

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return rootView;
    }
    public void refresh() {
        Call<MahasiswaList> MahasiswaCall = mApiInterface.getMahasiswa();
        MahasiswaCall.enqueue(new Callback<MahasiswaList>() {
            @Override
            public void onResponse(Call<MahasiswaList> call, Response<MahasiswaList>
                    response) {
                List<Mahasiswa> MahasiswaList = response.body().getMahasiswa();
                Log.d("Retrofit Get", "Jumlah data Mahasiswa: " +
                        String.valueOf(MahasiswaList.size()));
                mAdapter = new MahasiswaAdapter(MahasiswaList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MahasiswaList> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }



}

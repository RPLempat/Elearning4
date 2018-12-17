package com.example.nopride.elearning4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nopride.elearning4.model.Mahasiswa;
import com.example.nopride.elearning4.model.MahasiswaList;
import com.example.nopride.elearning4.remote.ApiClient;
import com.example.nopride.elearning4.remote.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Makul extends Fragment {
    private ArrayList<Mahasiswa> mahasiswa;


    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static Navigation ma;

    private RecyclerView recyclerView;
    private MahasiswaAdapter eAdapter;

    private Spinner th_ajar;
    private String[] tahun = {
            "2018/2019",
            "2017/2018",
            "2016/2017",
            "2015/2016"
    };
    private Spinner sem;
    private String[] semester= {
            "Ganjil",
            "Genap",
    };

    public Makul(){}
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.activity_matkul, container, false);

        th_ajar = (Spinner) view.findViewById(R.id.th_ajar);
        sem = (Spinner) view.findViewById(R.id.semester);

        getActivity().setTitle("Mata Kuliah");
        Log.e("Makul", "Makul");

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, tahun);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, semester);

        // mengeset Array Adapter tersebut ke Spinner
        th_ajar.setAdapter(adapter1);
        sem.setAdapter(adapter2);

        // mengeset listener untuk mengetahui saat item dipilih
        th_ajar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                Toast.makeText(getActivity(), "Selected "+ adapter1.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                Toast.makeText(getActivity(), "Selected "+ adapter2.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        refresh();

        return view;
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
                mAdapter = new MatkulAdapter(MahasiswaList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MahasiswaList> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}

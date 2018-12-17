package com.example.nopride.elearning4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Profile extends Fragment  {

    Fragment fragment;
    TextView nim,nama,prodi,fakultas;
    public Profile(){}
    LinearLayout view;
    SharedPreference sharedPreference ;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.activity_profile2, container, false);
        sharedPreference = new SharedPreference(getActivity());
        nim = view.findViewById(R.id.setNim);
        nama = view.findViewById(R.id.setNama);
        prodi = view.findViewById(R.id.setProdi);
        fakultas = view.findViewById(R.id.setFakultas);


        nim.setText(sharedPreference.getSPNim());
        nama.setText(sharedPreference.getSPNama());
        prodi.setText(sharedPreference.getSPProdi());
        fakultas.setText(sharedPreference.getSPFakultas());
        String urlGambar ="http://learningcoba.000webhostapp.com/profile/16650033.jpg";
        String urlPlaceholder ="http://learningcoba.000webhostapp.com/profile/16650033.jpg";

        imageView = (ImageView) view.findViewById(R.id.imageUser);
        Glide.with(this)
                // LOAD URL DARI INTERNET
                .load(urlGambar)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
//                .placeholder(R.drawable.uin)
//                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
//                .error(R.drawable.uin)
                .into(imageView);

        getActivity().setTitle("Profile");
        Log.e("Makul", "Profile");

        return view;
    }


}

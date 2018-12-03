package com.example.nopride.elearning4.remote;

import com.example.nopride.elearning4.model.MahasiswaList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("read.php")
    Call<MahasiswaList> getMahasiswa();


}

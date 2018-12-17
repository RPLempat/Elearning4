package com.example.nopride.elearning4;

import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import android.os.Handler;
import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

import com.example.nopride.elearning4.model.Mahasiswa;
import com.example.nopride.elearning4.model.MahasiswaList;
import com.example.nopride.elearning4.remote.ApiInterface;
import com.example.nopride.elearning4.remote.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    EditText ambilNIM, ambilPass; //Deklarasi object dari class EdiText
    String nim, pass, dbnim, dbnama, dbpass, dbjk, dbprodi, dbfakultas;
    ApiInterface mApiInterface;
    SharedPreference sharedPreference;
    Button login;
    Mahasiswa usermhs;
    Context mContext;
    ProgressDialog loading;
    int statusCode;
    //Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreference = new SharedPreference(this);
        usermhs = new Mahasiswa();
        mContext = this;

        login = findViewById(R.id.LOGIN);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        if (sharedPreference.getSPSudahLogin()) {
            startActivity(new Intent(Login.this, Navigation.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    private void requestLogin() {
        ambilNIM = (EditText) findViewById(R.id.NIM);
        ambilPass = (EditText) findViewById(R.id.Pass);
        nim = ambilNIM.getText().toString();
        pass = ambilPass.getText().toString();
        mApiInterface.getUser(nim, pass)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject hasil = new JSONObject(response.body().string());
                                if (hasil.getString("isSucces").equals("true")) {
                                    dbnim = hasil.getJSONObject("mahasiswa").getString("nim");
                                    dbnama = hasil.getJSONObject("mahasiswa").getString("nama");
                                    dbpass = hasil.getJSONObject("mahasiswa").getString("pass");
                                    dbjk =hasil.getJSONObject("mahasiswa").getString("pass");
                                    dbprodi = hasil.getJSONObject("mahasiswa").getString("prodi");
                                    dbfakultas = hasil.getJSONObject("mahasiswa").getString("fakultas");

                                    sharedPreference.saveSPString(sharedPreference.SP_NIM, dbnim);
                                    sharedPreference.saveSPString(sharedPreference.SP_NAMA, dbnama);
                                    sharedPreference.saveSPString(sharedPreference.SP_PASS, dbpass);
                                    sharedPreference.saveSPString(sharedPreference.SP_FAKULTAS, dbfakultas);
                                    sharedPreference.saveSPString(sharedPreference.SP_PRODI, dbprodi);



                                    // Shared Pref ini berfungsi untuk menjadi trigger session login
                                    sharedPreference.saveSPBoolean(sharedPreference.SP_SUDAH_LOGIN, true);
                                    startActivity(new Intent(Login.this, Navigation.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                } else {
                                    // Jika login gagal
                                    loading.dismiss();
                                    ;
                                    Toast.makeText(mContext, "Akun Tidak Tersedia", Toast.LENGTH_SHORT).show();
//
                                }
                            } catch (JSONException e) {
                                Toast.makeText(mContext, "Akun Tidak Tersedia", Toast.LENGTH_SHORT).show();
//                                e.printStackTrace();
                            } catch (IOException e) {
                                Toast.makeText(mContext, "Akun Tidak Tersedia", Toast.LENGTH_SHORT).show();
//                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                            //Toast.makeText(mContext, "Response Gagal 130", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "Response Gagal Load" + nim + " " + pass, Toast.LENGTH_SHORT).show();
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}
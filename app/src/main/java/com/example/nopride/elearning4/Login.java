package com.example.nopride.elearning4;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
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

    EditText ambilNIM ,ambilPass; //Deklarasi object dari class EdiText
    String nim,pass, dbnim, dbnama, dbpass, dbjk, dbprodi, dbfakultas;
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
                //loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        if (sharedPreference.getSPSudahLogin()){
            startActivity(new Intent(Login.this, Navigation.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    private void requestLogin(){
        ambilNIM = (EditText) findViewById(R.id.NIM);
        ambilPass = (EditText) findViewById(R.id.Pass);
        nim = ambilNIM.getText().toString();
        pass = ambilPass.getText().toString();
        mApiInterface.getUser(nim, pass)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "Response Sukses"+ nim+" "+pass, Toast.LENGTH_SHORT).show();
                        if (response.isSuccessful()){
                                Toast.makeText(mContext, "Response Suksesful " , Toast.LENGTH_SHORT).show();

                            //loading.dismiss();
                            try {
//                                Toast.makeText(mContext, "Response TRY", Toast.LENGTH_SHORT).show();
                                JSONObject hasil = new JSONObject(response.body().string());
                                if (hasil.getString("isSucces").equals("true")  ){
                                    dbnim = hasil.getJSONObject("mahasiswa").getString("nim");
                                    dbnama = hasil.getJSONObject("mahasiswa").getString("nama");
                                    dbpass = hasil.getJSONObject("mahasiswa").getString("pass");
                                    dbprodi = hasil.getJSONObject("mahasiswa").getString("prodi");
                                    dbfakultas = hasil.getJSONObject("mahasiswa").getString("fakultas");

                                    sharedPreference.saveSPString(sharedPreference.SP_NIM, dbnim);
                                    sharedPreference.saveSPString(sharedPreference.SP_NAMA, dbnama);
                                    // Shared Pref ini berfungsi untuk menjadi trigger session login
                                    sharedPreference.saveSPBoolean(sharedPreference.SP_SUDAH_LOGIN, true);

                                    startActivity(new Intent(Login.this, Navigation.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                } else {
//                                    // Jika login gagal
                                    Toast.makeText(mContext, "Akun Tidak Ada ", Toast.LENGTH_SHORT).show();
//                                    String error_message = jsonRESULTS.getString("error_msg");
//                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(mContext, "Response Gagal 1", Toast.LENGTH_SHORT).show();
//                                e.printStackTrace();
                            } catch (IOException e) {
                                Toast.makeText(mContext, "Response Gagal 2", Toast.LENGTH_SHORT).show();
//                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Response Gagal 130", Toast.LENGTH_SHORT).show();

                            //loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "Response Gagal Load"+ nim+" "+pass, Toast.LENGTH_SHORT).show();
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
//    public void User() {
//
//        mApiInterface.getUser(nim,pass)
//            .enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                        if (jsonRESULTS.getString("error").equals("false") ){
//                            //if ((etEmail.getText().toString()=="123")&&(etPassword.getText().toString()=="123")){
//                            // Jika login berhasil maka data nama yang ada di response API
//                            // akan diparsing ke activity selanjutnya.
//                            Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
//                            String nama = jsonRESULTS.getJSONObject("user").getString("nama");
//                            sharedPreference.saveSPString(sharedPreference.SP_NAMA, nama);
//                            // Shared Pref ini berfungsi untuk menjadi trigger session login
//                            sharedPreference.saveSPBoolean(sharedPreference.SP_SUDAH_LOGIN, true);
//
//                            startActivity(new Intent(getApplicationContext(), Navigation.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                            finish();
//                        } else {
//                            // Jika login gagal
//                            String error_message = jsonRESULTS.getString("error_msg");
//                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(Login.this, "before " + nim + " " + pass + " after " + dbnim + " " + dbpass , Toast.LENGTH_SHORT).show();
//                } else{
//                   Toast.makeText(Login.this, "before " + nim + " " + pass + " after Gagal " + dbnim + " " + dbpass, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                //Toast.makeText(Login.this, "before ", Toast.LENGTH_SHORT).show();
//                Log.e("Retrofit Get", t.toString());
//                dbpass= "Gagal";
//                Toast.makeText(Login.this, "before "+nim +" "+ pass +" after "+ dbnim +" "+dbpass+" after "+ statusCode , Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//       // dbpass = "123";
//         //Toast.makeText(this, "before "+nim +" "+ pass +" after "+ dbnim +" "+dbpass+" after "+ statusCode , Toast.LENGTH_SHORT).show();
//    }


//    public void loginMasuk(View view) {
//        ambilNIM = (EditText) findViewById(R.id.NIM);
//        ambilPass = (EditText) findViewById(R.id.Pass);
//        nim = ambilNIM.getText().toString();
//        pass = ambilPass.getText().toString();
//        User();
//        Toast.makeText(this, "before "+nim +" "+ pass +" after "+ dbnim +" "+dbpass, Toast.LENGTH_SHORT).show();
//
//        if ((nim.contains(dbnim))&&((pass.contains(dbpass)))) {
//            sharedPreference.saveSPBoolean(SharedPreference.SP_SUDAH_LOGIN, true);
//            Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Login.this, Navigation.class);
//            startActivity(intent);
//            finish();
//        }
//
//        else if ((nim.matches("")||pass.matches("")))
//
//        {
//
//            Toast.makeText(this, "Isikan Username dan Password", Toast.LENGTH_SHORT).show();
//
//        }
//
//        else {
//            //jika kedua kondisi diatas tidak memenuhi
//
//            Toast.makeText(this, "Login Gagal /Username Password Salah", Toast.LENGTH_SHORT).show();
//        }

//    }

}

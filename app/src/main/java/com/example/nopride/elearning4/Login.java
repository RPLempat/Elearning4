package com.example.nopride.elearning4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;


public class Login extends AppCompatActivity {

    EditText ambilNIM ,ambilPass; //Deklarasi object dari class EdiText
    String nim,pass; //Deklarasi object string

    SharedPreference sharedPreference;

    //Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreference = new SharedPreference(this);

        if (sharedPreference.getSPSudahLogin()){
            startActivity(new Intent(Login.this, Navigation.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    public void loginMasuk(View view) {

        ambilNIM = (EditText)findViewById(R.id.NIM);
        ambilPass = (EditText) findViewById(R.id.Pass);
        nim = ambilNIM.getText().toString();
        pass = ambilPass.getText().toString();



        if ((nim.contains("123"))&&((pass.contains("123")))) {
            sharedPreference.saveSPBoolean(SharedPreference.SP_SUDAH_LOGIN, true);
            Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, Navigation.class);
            startActivity(intent);
            finish();
        }

        else if ((nim.matches("")||pass.matches("")))

        {

            Toast.makeText(this, "Isikan Username dan Password", Toast.LENGTH_SHORT).show();

        }

        else {
            //jika kedua kondisi diatas tidak memenuhi

            Toast.makeText(this, "Login Gagal /Username Password Salah", Toast.LENGTH_SHORT).show();
        }

    }

}

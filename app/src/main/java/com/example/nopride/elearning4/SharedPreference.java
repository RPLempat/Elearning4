package com.example.nopride.elearning4;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreference {

    public static final String SP_MAHASISWA_APP = "spMahasiswaApp";

    public static final String SP_NIM = "spNim";
    public static final String SP_NAMA = "spNama";
    public static final String SP_PASS = "spPass";
    public static final String SP_JK = "spJk";
    public static final String SP_PRODI = "spProdi";
    public static final String SP_FAKULTAS = "spFakultas";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPreference(Context context){
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }
    public String getSPNim(){
        return sp.getString(SP_NIM, "");
    }
    public String getSPProdi(){
        return sp.getString(SP_PRODI, "");
    }
    public String getSPFakultas(){
        return sp.getString(SP_FAKULTAS, "");
    }
    public String getSPPass(){
        return sp.getString(SP_PASS, "");
    }
    public String getSPJk(){
        return sp.getString(SP_JK, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

}

package com.example.nopride.elearning4.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("jk")
    @Expose
    private String jk;
    @SerializedName("prodi")
    @Expose
    private String prodi;
    @SerializedName("fakultas")
    @Expose
    private String fakultas;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
}

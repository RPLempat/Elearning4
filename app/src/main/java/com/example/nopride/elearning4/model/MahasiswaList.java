package com.example.nopride.elearning4.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MahasiswaList {
    @SerializedName("Mahasiswa")
    @Expose
    private List<Mahasiswa> mahasiswa = null;

    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(List<Mahasiswa> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
}

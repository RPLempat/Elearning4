package com.example.nopride.elearning4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nopride.elearning4.model.Mahasiswa;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.CustomViewHolder>{
    private List<Mahasiswa> mahasiswas;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mahasiswa_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswas.get(position);
        holder.employeeName.setText(mahasiswa.getNim());
        holder.email.setText(mahasiswa.getNama());
        holder.designation.setText(mahasiswa.getProdi());
        holder.salary.setText(mahasiswa.getFakultas());
        holder.dob.setText(mahasiswa.getPass());
        holder.contactNumber.setText(mahasiswa.getJk());
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView employeeName, designation, email, salary, dob,contactNumber;

        public CustomViewHolder(View view) {
            super(view);
            employeeName = (TextView) view.findViewById(R.id.employeeName);
            email = (TextView) view.findViewById(R.id.email);
            designation = (TextView) view.findViewById(R.id.designation);
            salary = (TextView) view.findViewById(R.id.salary);
            dob = (TextView) view.findViewById(R.id.dob);
            contactNumber = (TextView) view.findViewById(R.id.contactNumber);
        }
    }
}

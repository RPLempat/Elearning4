package com.example.nopride.elearning4;

<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> origin/master
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nopride.elearning4.model.Mahasiswa;
import java.util.List;

<<<<<<< HEAD
public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder>{
    List<Mahasiswa> mMahasiswaList;

    public MahasiswaAdapter(List <Mahasiswa> MahasiswaList) {
        mMahasiswaList = MahasiswaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mahasiswa, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }
    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewNim.setText("NIM = " + mMahasiswaList.get(position).getNim());
        holder.mTextViewNama.setText("NAMA = " + mMahasiswaList.get(position).getNama());
        holder.mTextViewPass.setText("PASS = " + mMahasiswaList.get(position).getPass());
        holder.mTextViewJk.setText("JK = " + mMahasiswaList.get(position).getJk());
        holder.mTextViewProdi.setText("PRODI = " + mMahasiswaList.get(position).getProdi());
        holder.mTextViewFakultas.setText("FAKULTAS = " + mMahasiswaList.get(position).getFakultas());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(view.getContext(), );
//                mIntent.putExtra("Nim", mMahasiswaList.get(position).getNim());
//                mIntent.putExtra("Nama", mMahasiswaList.get(position).getNama());
//                mIntent.putExtra("Pass", mMahasiswaList.get(position).getPass());
//                mIntent.putExtra("Jk", mMahasiswaList.get(position).getJk());
//                mIntent.putExtra("Prodi", mMahasiswaList.get(position).getProdi());
//                mIntent.putExtra("Fakultas", mMahasiswaList.get(position).getFakultas());
//                view.getContext().startActivity(mIntent);
//            }
//        });
    }

    @Override
    public int getItemCount () {
        return mMahasiswaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNim, mTextViewNama, mTextViewPass, mTextViewJk, mTextViewProdi, mTextViewFakultas;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNim = (TextView) itemView.findViewById(R.id.nim);
            mTextViewNama = (TextView) itemView.findViewById(R.id.nama);
            mTextViewPass = (TextView) itemView.findViewById(R.id.pass);
            mTextViewJk = (TextView) itemView.findViewById(R.id.jk);
            mTextViewProdi = (TextView) itemView.findViewById(R.id.prodi);
            mTextViewFakultas = (TextView) itemView.findViewById(R.id.fakultas);
        }
    }
}
=======
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
>>>>>>> origin/master

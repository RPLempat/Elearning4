package com.example.nopride.elearning4;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nopride.elearning4.model.Mahasiswa;
import java.util.List;

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
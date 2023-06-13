package com.example.assignment.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.DAO.DangKyDAO;
import com.example.assignment.DAO.MonHocDAO;
import com.example.assignment.R;
import com.example.assignment.model.MonHocModel;

import java.util.ArrayList;

public class DangKyAdapter extends RecyclerView.Adapter<DangKyAdapter.ViewHolder> {

    private final Context context;
    private Dialog customDialog;
    private final ArrayList<MonHocModel> data;
    private final ArrayList<String> list;
    private final String iduser;

    private DangKyDAO dangKyDAO;

    private MonHocDAO monHocDAO;

    public DangKyAdapter(Context context, ArrayList<MonHocModel> data, ArrayList<String> list,String iduser) {
        this.context = context;
        this.data = data;
        this.list = list;
        this.iduser = iduser;
    }

    public void clearData() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View monhocView = inflater.inflate(R.layout.layout_item, parent, false);
        DangKyAdapter.ViewHolder viewHolder = new DangKyAdapter.ViewHolder(monhocView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}

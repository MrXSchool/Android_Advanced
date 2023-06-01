package com.example.assignment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.model.MonHocModel;

import java.util.ArrayList;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.ViewHolder>{
    private Context context;
    private ArrayList<MonHocModel> data;
    private ArrayList<String> list;

    public MonHocAdapter(Context context, ArrayList<MonHocModel> data, ArrayList<String> list) {
        this.context = context;
        this.data = data;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View monhocView = inflater.inflate(R.layout.layout_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(monhocView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MonHocModel monhoc = data.get(position);
        holder.tvTenMonHoc.setText(monhoc.getName());
        holder.tvCode.setText(monhoc.getCode());
        switch (monhoc.getCode()) {
            case "MOB306":
                holder.imgMonHoc.setImageResource(R.mipmap.react);
                break;
            case "MOB201":
                holder.imgMonHoc.setImageResource(R.mipmap.android_advanced);
                break;
            case "MOB2041":
                holder.imgMonHoc.setImageResource(R.mipmap.project);
                break;

        }
        Log.d("TAG", "onBindViewHolder: "+list);
        if(list.contains(monhoc.getCode())){
            holder.btnDangKy.setText("Đã đăng ký");
        }
        else{
            holder.btnDangKy.setText("Đăng ký");
        }
        holder.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btnDangKy.getText().equals("Đăng ký")){
                    holder.btnDangKy.setText("Đã đăng ký");
                    list.add(monhoc.getCode());
                }
                else{
                    holder.btnDangKy.setText("Đăng ký");
                    list.remove(monhoc.getCode());
                }
            }
        });
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMonHoc,info;
        TextView tvTenMonHoc;
        TextView tvCode;
        Button btnDangKy;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgMonHoc = itemView.findViewById(R.id.iv_avatar_monhoc);
            tvTenMonHoc = itemView.findViewById(R.id.tv_name_monhoc);
            tvCode = itemView.findViewById(R.id.tv_code_monhoc);
            btnDangKy = itemView.findViewById(R.id.btn_dangky);
            info = itemView.findViewById(R.id.iv_Info_monhoc1);

        }
    }
}

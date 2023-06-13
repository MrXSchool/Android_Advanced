package com.example.assignment.Adapter;

import android.app.Dialog;
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

import com.example.assignment.DAO.DangKyDAO;
import com.example.assignment.DAO.MonHocDAO;
import com.example.assignment.R;
import com.example.assignment.model.ChiTietMonHoc;
import com.example.assignment.model.DangKyModel;
import com.example.assignment.model.MonHocModel;

import java.util.ArrayList;

public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.ViewHolder>{
    private final Context context;
    private Dialog customDialog;
    private final ArrayList<MonHocModel> data;
    private final ArrayList<String> list;
    private final String iduser;

    private DangKyDAO dangKyDAO;

    private MonHocDAO monHocDAO;
    private String trangthai;

    public MonHocAdapter(Context context, ArrayList<MonHocModel> data, ArrayList<String> list,String iduser,String trangthai) {
        this.context = context;
        this.data = data;
        this.list = list;
        this.iduser = iduser;
        this.trangthai = trangthai;
    }

    public void clearData() {
//        if (data != null) {
            data.clear();
            notifyDataSetChanged();
//        }
    }

    public ArrayList<MonHocModel> chinhsuadata(ArrayList<MonHocModel> data, ArrayList<String> list){
        switch (trangthai){
            case "all":
                break;

            case "yes":
                //xoa nhung mon hoc trong data khong co trong list
                for(int i = 0;i<data.size();i++){
                    if(!list.contains(data.get(i).getCode())){
                        data.remove(i);
                        i--;
                    }
                }
                break;
            case "no":
                //xoa nhung mon hoc trong data co trong list
                for(int i = 0;i<data.size();i++){
                    if(list.contains(data.get(i).getCode())){
                        data.remove(i);
                        i--;
                    }
                }
                break;

        }
        return data;
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
        customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.layout_customdialog);




        MonHocModel monhoc = chinhsuadata(data,list).get(position);
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
                    dangKyDAO = new DangKyDAO(context);
                    dangKyDAO.insert(Integer.parseInt(iduser),monhoc.getCode());
                }
                else{
                    holder.btnDangKy.setText("Đăng ký");
                    dangKyDAO = new DangKyDAO(context);
                    dangKyDAO.delete(Integer.parseInt(iduser),monhoc.getCode());
                }
            }
        });
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Dialog
                TextView tvTenMonHoc = customDialog.findViewById(R.id.txtTenMonHoc);
                TextView tvCode = customDialog.findViewById(R.id.txtMaMonHoc);
                TextView tvTenGiangVien = customDialog.findViewById(R.id.txtTenGiangVien);
                TextView tvLichHoc = customDialog.findViewById(R.id.txtLicHoc);
                TextView tvPhongHoc = customDialog.findViewById(R.id.txtPhongHoc);
                Log.d("TAG: holder.info", "onClick: "+monhoc.getCode());
                monHocDAO = new MonHocDAO(context);
                Log.d("TAG: holder.info", "onClick: "+monHocDAO.getbycode(monhoc.getCode()));
                ChiTietMonHoc ctmh = monHocDAO.getbycode(monhoc.getCode());



                //ChiTietMonHoc(String code1, String name, String teacher, String date, String room)
                tvTenMonHoc.setText("Tên Môn học: "+ctmh.getName());
                tvCode.setText("Code: "+ctmh.getCode1());
                tvTenGiangVien.setText("Giảng Viên: "+ctmh.getTeacher());
                tvLichHoc.setText("Thời Gian Học: "+ctmh.getDate());
                tvPhongHoc.setText("Phòng Học: "+ctmh.getRoom());
                customDialog.show();


            }
        });

        //nếu trạng thái là "all" thì item sẽ hiển thị lên recyclerview
//        if(trangthai.equals("all")){
//            holder.itemView.setVisibility(View.VISIBLE);
//        }
//        //nếu trạng thái là "yes" thì hiển thị những môn học đã đăng ký
//        else if(trangthai.equals("yes")){
//            if(list.contains(monhoc.getCode())){
//                holder.itemView.setVisibility(View.VISIBLE);
//            }
//            else{
//                holder.itemView.setVisibility(View.GONE);
//            }
//        }
//        //nếu trạng thái là "no" thì hiển thị những môn học chưa đăng ký
//        else if(trangthai.equals("no")){
//            if(list.contains(monhoc.getCode())){
//                holder.itemView.setVisibility(View.GONE);
//
//
//            }
//            else{
//                holder.itemView.setVisibility(View.VISIBLE);
//
//            }
//        }

//        switch (trangthai){
//            case "all":
//                holder.itemView.setVisibility(View.VISIBLE);
//                break;
//            case "yes":
//                if(list.contains(monhoc.getCode())){
//                    holder.itemView.setVisibility(View.VISIBLE);
//                }
//                else{
//                    holder.itemView.setVisibility(View.GONE);
//                }
//                break;
//            case "no":
//                if(list.contains(monhoc.getCode())){
//                    holder.itemView.setVisibility(View.GONE);
//                }
//                else{
//                    holder.itemView.setVisibility(View.VISIBLE);
//                }
//                break;
//        }


    }


    @Override
    public int getItemCount() {
        return chinhsuadata(data,list).size();
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

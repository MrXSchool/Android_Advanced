package com.example.assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.model.sample2.Sample2;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AdapterAPI extends RecyclerView.Adapter<AdapterAPI.ViewHolder>{

    private final Context context;

    private final Sample2 sample2;


    public AdapterAPI(Context context, Sample2 sample2) {
        this.context = context;
        this.sample2 = sample2;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View apiView = inflater.inflate(R.layout.itemlayout_sample2, parent, false);
        ViewHolder viewHolder = new ViewHolder(apiView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(sample2.getChannel().getItem().get(position).getTitle());
        holder.tvPubDate.setText(sample2.getChannel().getItem().get(position).getPubDate());
//        holder.tvLink.setText(sample2.getChannel().getItem().get(position).getLink());
//        holder.tvGuid.setText(sample2.getChannel().getItem().get(position).getGuid());
        int cmt = sample2.getChannel().getItem().get(position).getComments();
        holder.tvComments.setText(cmt+"");

        // dùng thư viện Picasso để load hình ảnh từ URL
        String htmlString = sample2.getChannel().getItem().get(position).getDescription().get__cdata();
        Document document = Jsoup.parse(htmlString);// parse HTML bằng Jsoup
        Element imgElement = document.select("img").first();
        if (imgElement != null) {
            String imageUrl = imgElement.attr("src");
            // imageUrl chứa URL của hình ảnh
            Log.d("imageUrl here>>>>>>>>>", "handleResponse: "+imageUrl);
//            System.out.println("Image URL: " + imageUrl);
            Picasso.get().load(imageUrl).into(holder.imgView);
        } else {
            // Không tìm thấy phần tử img trong chuỗi HTML

        }
        //dùng jsoup để lấy nội dung trong thẻ cdata
        Element textElement = document.body();
        String text = textElement.text();
        // text chứa đoạn văn bản cần lấy
        holder.tvDescription.setText(text);

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, com.example.assignment.activity.WebviewActivity.class);
                intent.putExtra("link", sample2.getChannel().getItem().get(position).getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sample2.getChannel().getItem().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //"title": "Quản lý trường mầm non bị tố bỏ đói, bạo hành trẻ",
        //        "description": {
        //          "__cdata": "<a href=\"https://vnexpress.net/quan-ly-truong-mam-non-bi-to-bo-doi-bao-hanh-tre-4518723.html\"><img src=\"https://i1-vnexpress.vnecdn.net/2022/10/03/-2774-1664795211.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=lD4_qBW5VWLseAnb6riS8g\" ></a></br>Quản lý nhóm lớp tư thục Elm School được cho là đã kéo lê, đút cơm thô bạo, để trẻ chịu đói, khát."
        //        },
        //        "pubDate": "Tue, 04 Oct 2022 07:54:52 +0700",
        //        "link": "https://vnexpress.net/quan-ly-truong-mam-non-bi-to-bo-doi-bao-hanh-tre-4518723.html",
        //        "guid": "https://vnexpress.net/quan-ly-truong-mam-non-bi-to-bo-doi-bao-hanh-tre-4518723.html",
        //        "comments": 0
        TextView tvTitle, tvDescription,  tvPubDate, tvComments;
        ImageView imgView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textView_sample2_title);
            tvDescription = itemView.findViewById(R.id.textView_sample2_description);
//            tvLink = itemView.findViewById(R.id.textView_sample2_link);
            tvPubDate = itemView.findViewById(R.id.textView_sample2_pubDate);
//            tvGuid = itemView.findViewById(R.id.textView_sample2_guid);
            tvComments = itemView.findViewById(R.id.textView_sample2_comments);
            imgView = itemView.findViewById(R.id.imageView_sample2);



        }
    }



}


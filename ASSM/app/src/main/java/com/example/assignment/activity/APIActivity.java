package com.example.assignment.activity;

import static com.example.assignment.Interface.ServiceInterface.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.assignment.Adapter.AdapterAPI;
import com.example.assignment.Interface.ServiceInterface;
import com.example.assignment.R;
import com.example.assignment.model.Sample1_all;
import com.example.assignment.model.Sample1_data;
import com.example.assignment.model.sample2.Sample2;
import com.example.assignment.model.sample2.Sample2_channel;
import com.example.assignment.model.sample2.Sample2_item;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIActivity extends AppCompatActivity {
    ImageView imageView_sample2;
    RecyclerView recyclerView_sample2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);
        imageView_sample2 = findViewById(R.id.imageView_sample2);
        CallAPI_Sample2();
        recyclerView_sample2 = findViewById(R.id.rv_API);


    }

//    private void demoCallAPI() {
//
//        ServiceInterface requestInterface = new Retrofit.Builder()
//                .baseUrl(BASE_SERVICE)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(ServiceInterface.class);
//
//        new CompositeDisposable().add(requestInterface.getSample1()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::handleResponse, this::handleError)
//        );
//    }



    private void CallAPI_Sample2() {

        ServiceInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceInterface.class);

        new CompositeDisposable().add(requestInterface.getSample2()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Sample2 sample2) {
        //khi gọi API THÀNH CÔNG thì thực hiện xử lý ở đây

        //picasso
//        String htmlString = sample2.getChannel().getItem().get(0).getDescription().get__cdata();
//
//        Document document = Jsoup.parse(htmlString);
//        Element imgElement = document.select("img").first();
//        if (imgElement != null) {
//            String imageUrl = imgElement.attr("src");
//            // imageUrl chứa URL của hình ảnh
//            Log.d("imageUrl here>>>>>>>>>", "handleResponse: "+imageUrl);
////            System.out.println("Image URL: " + imageUrl);
//            Picasso.get().load(imageUrl).into(imageView_sample2);
//        } else {
//            // Không tìm thấy phần tử img trong chuỗi HTML
//
//        }
        AdapterAPI adapterAPI = new AdapterAPI(APIActivity.this,sample2);

        recyclerView_sample2.setAdapter(adapterAPI);
        recyclerView_sample2.setLayoutManager(new LinearLayoutManager(APIActivity.this));
    }


    private void handleError(Throwable error) {
        String a = "";
        //khi gọi API KHÔNG THÀNH CÔNG thì thực hiện xử lý ở đây
        Log.d("TAG", "handleError: "+error.getMessage());
    }
}
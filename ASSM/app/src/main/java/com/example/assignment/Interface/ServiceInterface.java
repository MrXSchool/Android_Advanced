package com.example.assignment.Interface;

import com.example.assignment.model.Sample1_all;
import com.example.assignment.model.sample2.Sample2;
import com.example.assignment.model.sample2.Sample2_channel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceInterface {

    String BASE_SERVICE = "https://apis.dinhnt.com/";
//    @GET("sample1.json")
//    Observable<Sample1_all> getSample1();

    @GET("edu.json")
    Observable<Sample2> getSample2();
}


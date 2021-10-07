package com.example.zunezxapp.api;

import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.LoginRespone;
import com.example.zunezxapp.entity.User;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
//    @GET("posts")
//    Single<ArrayList<Post>> getListPosts();
//
//    @POST("/api/auths/customer/login")
//    Single<LoginResponse> login(@Body RequestBody loginResponse);

    @POST("/api/auths/customer/login")
    Single<BaseObjectResponse<User>> userLogin(@Header("Authorization") String basicAuth,
                                               @Body LoginRespone loginRespone);
}

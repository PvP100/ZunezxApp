package com.example.zunezxapp.api;

import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.HomeCategory;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.LoginRespone;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.entity.ProductDetail;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.base.entity.Result;
import com.example.zunezxapp.entity.User;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
//    @GET("posts")
//    Single<ArrayList<Post>> getListPosts();
//
//    @POST("/api/auths/customer/login")
//    Single<LoginResponse> login(@Body RequestBody loginResponse);

    @POST("/api/auths/customer/login")
    Single<BaseObjectResponse<User>> userLogin(@Header("Authorization") String basicAuth,
                                               @Body LoginRespone loginRespone);

    @GET("/api/customers/profiles/{id}")
    Single<BaseObjectResponse<Profile>> getProfile(@Path("id") String userId);

    @GET("/api/category/categories")
    Single<BaseObjectResponse<Result<HomeCategory>>> getHomeCategory();

    @GET("/api/products/categorys/{id}")
    Single<BaseObjectResponse<Result<HomeProduct>>> getHomeProduct(@Path("id") int cateId);

    @GET("/api/products/clothes/{id}")
    Single<BaseObjectResponse<ProductDetail>> getProductDetail(@Path("id") String productId);
}

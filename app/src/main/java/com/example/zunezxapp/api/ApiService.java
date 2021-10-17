package com.example.zunezxapp.api;

import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.HomeCategory;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.LoginRespone;
import com.example.zunezxapp.entity.NewPassword;
import com.example.zunezxapp.entity.Order;
import com.example.zunezxapp.entity.ProductDetail;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.base.entity.Result;
import com.example.zunezxapp.entity.ProfileBody;
import com.example.zunezxapp.entity.User;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
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

    @GET("/api/customers/{customerId}/orders")
    Single<BaseObjectResponse<Result<Order>>> getCustomerOrder(@Path("customerId") String customerId);

    @POST("/api/auths/customer/{customerId}/newPassword")
    Single<BaseObjectResponse> changePassword(@Path("customerId") String customerId,
                                              @Body NewPassword newPassword);

    @GET("/api/products/searchProduct/{productName}")
    Single<BaseObjectResponse<Result<HomeProduct>>> searchProduct(@Path("productName") String productName);

    @PUT("/api/customers/updateProfile/{id}")
    Single<BaseObjectResponse<Profile>> updateProfile(@Path("id") String userid,
                                                      @Body ProfileBody profileBody);

    @POST("/api/auths/customers/register")
    Single<BaseObjectResponse> register(@Header("Authorization") String basicAuth,
                                                 @Body ProfileBody profileBody);
}

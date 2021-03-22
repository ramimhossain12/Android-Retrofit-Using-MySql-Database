package com.example.datacrudmysql;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("add.php")
    Call<List<Post>> getPosts();

    @POST("insert.php")
    Call<Post> createPost(@Body Post post);

    @PUT("posts/{id}")
    Call<Post> putPost(@Header("Dynamic-Header") String header,
                       @Path("id") int id,
                       @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@HeaderMap Map<String,String> headers ,
                         @Path("id") int id,
                         @Body Post post);
}

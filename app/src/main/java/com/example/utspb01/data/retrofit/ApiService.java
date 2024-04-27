package com.example.utspb01.data.retrofit;

import com.example.utspb01.data.response.GithubResponse;
import com.example.utspb01.data.response.GithubUser;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    Call<GithubResponse> searchUsers(@Query("q") String query);


    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);
}

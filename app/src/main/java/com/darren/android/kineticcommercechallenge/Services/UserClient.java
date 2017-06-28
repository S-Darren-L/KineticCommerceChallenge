package com.darren.android.kineticcommercechallenge.Services;

import com.darren.android.kineticcommercechallenge.DataModels.RandomUserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Darren on 6/26/2017.
 */

public interface UserClient {

    @GET(".")
    Call<RandomUserResponse> repositoryOfUsers(@Query("format") String format, @Query("results") int resultNumber);

    @Streaming
    @GET
    Call<ResponseBody> loadImageStream(@Url String url);
}

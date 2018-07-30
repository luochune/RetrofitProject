package com.example.luochune.retrofitproject;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import static com.example.luochune.retrofitproject.BuildConfig.BaseUrl;

/**
 *
 */
public interface RemoteApi {
    String baseUrl=BaseUrl;
    @GET("{url}")
    Observable<String> loadString(@Path(value = "url", encoded = true) String url);

    @POST
    Observable<String> postString(@Url String url, @QueryMap Map<String, String> params);
}
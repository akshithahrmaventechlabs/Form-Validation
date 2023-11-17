package com.example.formvalidation;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) { // Use a single equal sign here
            retrofit = new Retrofit.Builder() // Use a single equal sign here
                    .baseUrl(" https://api.npoint.io/b8bb67258d14242e04ad")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

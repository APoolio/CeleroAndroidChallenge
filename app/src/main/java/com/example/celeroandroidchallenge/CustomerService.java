package com.example.celeroandroidchallenge;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerService
{

    private static String customerBaseURL = "https://hulet.tech";
    public static Retrofit retrofit;

    public CustomerService(){}

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(customerBaseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }



}

package com.example.celeroandroidchallenge;

import com.example.celeroandroidchallenge.Models.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CustomerAPI
{
    @GET("/celerocustomers.json")
    Call<List<Customer>> getAllNames();
}

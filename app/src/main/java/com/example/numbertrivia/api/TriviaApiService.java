package com.example.numbertrivia.api;

import com.example.numbertrivia.data.Trivia;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface where all the functions are for making http requests.
 */
public interface TriviaApiService {
    @GET("/random/trivia?json&max=9999")
    Call<Trivia> getTrivia();
//    @GET("/random/date?json")
//    Call<Trivia> getDate();
//    @GET("/random/math?json")
//    Call<Trivia> getMath();
//    @GET("/random/year?json")
//    Call<Trivia> getYear();
}
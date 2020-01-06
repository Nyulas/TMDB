package com.example.tmdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("search/movie?api_key=0e190e3f5594ff2fbc1943deefb93196")

    Call<MovieResults> getAllData(@Query("query")String query);

    @GET("movie/popular")
    Call<MovieResults> getPopularMovies(@Query("api_key") String apiKey, @Query ("page") int page);

    /*@GET("movie/{category}")
    Call<MovieResults> listofMovies(
            @Path("category") String category, @Query("apikey") String apiKey, @Query("language") String LANGUAGE,
            @Query("page") int PAGE);*/
}

package com.example.ricar.httprequests.api;

import com.example.ricar.httprequests.model.Foto;
import com.example.ricar.httprequests.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();

}

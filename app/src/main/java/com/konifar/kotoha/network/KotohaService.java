package com.konifar.kotoha.network;

import com.konifar.kotoha.models.pojo.Phrase;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface KotohaService {

    @GET("/api/phrases.json")
    Observable<List<Phrase>> getPhrasesByText(@Query("text") String text);

    @GET("/api/phrases.json")
    Call<List<Phrase>> getPhrasesByTag(@Query("tag") String tag);

}

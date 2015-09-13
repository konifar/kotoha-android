package com.konifar.kotoha.models;

import com.konifar.kotoha.MainApplication;
import com.konifar.kotoha.models.pojo.Phrase;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PhraseModel {

    public Subscription getListByText(String searchText, Observer<List<Phrase>> observer) {
        return MainApplication.API.getPhrasesByText(searchText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public Subscription getListByTag(String searchText, Observer<List<Phrase>> observer) {
        return MainApplication.API.getPhrasesByTag(searchText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

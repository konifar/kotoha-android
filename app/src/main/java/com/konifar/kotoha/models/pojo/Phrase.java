package com.konifar.kotoha.models.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Phrase extends Model {

    private String text;
    @SerializedName("tag_list")
    private List<String> tagList;

    public String getText() {
        return text;
    }

    public List<String> getTagList() {
        return tagList;
    }

}

package io.github.aerhakim.bacaberita.utils.api;

import java.util.List;

import io.github.aerhakim.bacaberita.models.Article;

public interface DataListener<Response>{
    void onFetchData(List<Article> list, String message);
    void onError(String message);
}

package com.example.pureupdate_newsapp.Models;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {

    void onFetchData(List<NewsHeadline> list, String message);
    void onError(String message);
}

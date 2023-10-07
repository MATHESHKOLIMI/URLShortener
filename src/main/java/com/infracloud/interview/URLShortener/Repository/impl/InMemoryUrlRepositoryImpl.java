package com.infracloud.interview.URLShortener.Repository.impl;

import com.infracloud.interview.URLShortener.Repository.URLRepository;
import com.infracloud.interview.URLShortener.model.Url;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class InMemoryUrlRepositoryImpl implements URLRepository {

    Map<Url, Url> urlMap = new HashMap<>();
    Map<Url, Url> shortUrlMap = new HashMap<>();


    @Override
    public Map<Url, Url> getUrlMap() {
        return urlMap;
    }

    @Override
    public Map<Url, Url> getShortUrlMap() {
        return shortUrlMap;
    }
}

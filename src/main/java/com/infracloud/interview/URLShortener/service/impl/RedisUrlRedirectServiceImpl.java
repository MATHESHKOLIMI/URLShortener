package com.infracloud.interview.URLShortener.service.impl;

import com.infracloud.interview.URLShortener.Repository.URLRepositoryDB;
import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.RedisURLRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisUrlRedirectServiceImpl implements RedisURLRedirectService {
    @Autowired
    URLRepositoryDB urlRepositoryDB;


    @Override
    public Url redirectUrl(Url url, boolean isShortUrl) {
        try{
            Url urlObject = new Url(urlRepositoryDB.getUrl(url, true));
            return urlObject;
        }catch (Exception e){
            e.printStackTrace();
            return new Url("Not Mapped to any URL");
        }
    }
}

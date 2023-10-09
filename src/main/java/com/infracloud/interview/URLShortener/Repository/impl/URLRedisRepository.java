package com.infracloud.interview.URLShortener.Repository.impl;

import com.infracloud.interview.URLShortener.Repository.URLRepository;
import com.infracloud.interview.URLShortener.Repository.URLRepositoryDB;
import com.infracloud.interview.URLShortener.model.Domain;
import com.infracloud.interview.URLShortener.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class URLRedisRepository implements URLRepositoryDB {

    @Autowired
    RedisTemplate redisTemplate;

    public static final String URL = "URL";
    public static final String SHORTURL = "SHORTURL";
    public static final String DOMAIN = "DOMAIN";


    public void addUrlToDataBase(Url url){
//        redisTemplate.opsForHash().put(KEY, url.getUrl(), url);
    }

    @Override
    public String getUrl(Url url, boolean isShortUrl) {
        String urlObject;
        if(isShortUrl){
            urlObject = (String) redisTemplate.opsForHash().get(SHORTURL, url.getUrl());
        }else{
            urlObject = (String) redisTemplate.opsForHash().get(URL, url.getUrl());
        }
        return urlObject;
    }

    @Override
    public void saveUrl(Url url1, Url url2, boolean isShortUrl) {
        if(isShortUrl){
            redisTemplate.opsForHash().put(SHORTURL, url1.getUrl(), url2.getUrl() );
        }else{
            redisTemplate.opsForHash().put(URL, url1.getUrl(), url2.getUrl());
        }
    }

    @Override
    public void updateDomainMetrics(String domain){
        if(null != redisTemplate.opsForHash().get(DOMAIN,domain)){
            Integer value = (Integer) redisTemplate.opsForHash().get(DOMAIN,domain);
            value++;
            redisTemplate.opsForHash().put(DOMAIN, domain, value);
        }else{
            redisTemplate.opsForHash().put(DOMAIN, domain, Integer.valueOf(1));
        }
    }

    @Override
    public Map<String, Integer> getDomainMetrics() {

        Map<String,Integer> domainMap = redisTemplate.opsForHash().entries(DOMAIN);
        return domainMap;
    }
}

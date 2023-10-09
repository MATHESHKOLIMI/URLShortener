package com.infracloud.interview.URLShortener.service.impl;

import com.infracloud.interview.URLShortener.Repository.URLRepositoryDB;
import com.infracloud.interview.URLShortener.model.Domain;
import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.RedisUrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisUrlShortenerServiceImpl implements RedisUrlShortenerService {

    @Autowired
    URLRepositoryDB urlRepositoryDB;

    @Override
    public Url shortenerUrl(Url url, boolean isShortUrl) {
        if(null != urlRepositoryDB.getUrl(url, false)){
            urlRepositoryDB.updateDomainMetrics(getDomain(url));
            return new Url(urlRepositoryDB.getUrl(url, false));
        }else{
            List<String> domainList = Domain.getInstance().domainList();
            StringBuilder domainUrl = new StringBuilder(domainList.get(0));
            StringBuilder hashCodeUrl = new StringBuilder(String.valueOf(Math.abs(url.hashCode())));
            Url shortUrl = new Url(domainUrl.append(hashCodeUrl).toString());
            urlRepositoryDB.saveUrl(url, shortUrl, false );
            urlRepositoryDB.saveUrl(shortUrl,url, true);
            urlRepositoryDB.updateDomainMetrics(getDomain(url));
            return shortUrl;
        }
    }

    @Override
    public Map<String, Integer> getDomainMetrics(){
        return urlRepositoryDB.getDomainMetrics();
    }

    public String getDomain(Url url){
        String domain= null;
        String originalUrl = url.getUrl();
        String [] strArr= originalUrl.split("\\.");
        if(strArr[0].contains("www")){
            domain = strArr[1];
        }else{
            domain = strArr[0].split("//")[1];
        }
        return domain;
    }

}

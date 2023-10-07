package com.infracloud.interview.URLShortener.service.impl;

import com.infracloud.interview.URLShortener.Repository.URLRepository;
import com.infracloud.interview.URLShortener.model.Domain;
import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    URLRepository urlRepository;

    @Override
    public Url shortenerUrl(Url url) {
        Map<Url, Url> urlMap = urlRepository.getUrlMap();
        Map<Url, Url> shortUrlMap = urlRepository.getShortUrlMap();
        if(urlMap.containsKey(url)){
            return urlMap.get(url);
        }
        else{
            Random random = new Random();
            List<String> domainList = Domain.getInstance().domainList();
            StringBuilder domainUrl = new StringBuilder(domainList.get(random.nextInt(domainList.size())));
            StringBuilder hashCodeUrl = new StringBuilder(String.valueOf(Math.abs(url.hashCode())));
            Url shortUrl = new Url(domainUrl.append(hashCodeUrl).toString());
            urlMap.put(url, shortUrl);
            shortUrlMap.put(shortUrl, url);
            return shortUrl;
        }
    }
}

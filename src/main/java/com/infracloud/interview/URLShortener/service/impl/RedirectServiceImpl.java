package com.infracloud.interview.URLShortener.service.impl;

import com.infracloud.interview.URLShortener.Repository.URLRepository;
import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class RedirectServiceImpl implements RedirectService {

    @Autowired
    URLRepository urlRepository;

    @Override
    public Optional<Url> redirectUrl(Url url) {
        Map<Url, Url> urlMap = urlRepository.getUrlMap();
        Map<Url, Url> shortUrlMap = urlRepository.getShortUrlMap();
        try{
            if(shortUrlMap.containsKey(url)){
                if(urlMap.get(shortUrlMap.get(url)).equals(url)){
                    return Optional.of(shortUrlMap.get(url));
                }
            }else{
                return Optional.of(new Url("Not Mapped TO Any Url"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.of((shortUrlMap.get(url)));
    }
}

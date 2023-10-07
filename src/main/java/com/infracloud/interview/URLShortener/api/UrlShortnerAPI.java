package com.infracloud.interview.URLShortener.api;


import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortnerAPI {
    @Autowired
    UrlShortenerService urlShortenerService;

    @GetMapping("/shortener")
    public ResponseEntity<Url> getShortenedUrl(@RequestBody Url url){
        try {
            Url shortUrl = urlShortenerService.shortenerUrl(url);
            return new ResponseEntity(shortUrl, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

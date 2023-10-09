package com.infracloud.interview.URLShortener.api.V2;

import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.RedisUrlShortenerService;
import com.infracloud.interview.URLShortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("V2")
public class UrlShortenerAPI {

    @Autowired
    RedisUrlShortenerService redisUrlShortenerService;

    @GetMapping("/shortener")
    public ResponseEntity<Url> getShortUrl(@RequestBody Url url){
        try {
            Url shortUrl = redisUrlShortenerService.shortenerUrl(url, false);
            return new ResponseEntity(shortUrl, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/metrics")
    public ResponseEntity<Map<String,Integer>> getMetrics(){
        try{
            return new ResponseEntity<>(redisUrlShortenerService.getDomainMetrics(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

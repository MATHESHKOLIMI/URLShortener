package com.infracloud.interview.URLShortener.api;

import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.RedirectService;
import com.infracloud.interview.URLShortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RedirectAPI {

    @Autowired
    RedirectService redirectService;

    @GetMapping("/redirect")
    public ResponseEntity<Url> getRedirectedUrl(@RequestBody Url shortUrl){
        try {
            Optional<Url> url = redirectService.redirectUrl(shortUrl);
            if(url.get() != null && !url.get().getUrl().contains("Not Mapped")) {
                return new ResponseEntity(url.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(url.get(), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

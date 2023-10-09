package com.infracloud.interview.URLShortener.api.V2;

import com.infracloud.interview.URLShortener.model.Url;
import com.infracloud.interview.URLShortener.service.RedisURLRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("V2")
public class RedirectApi {
    @Autowired
    RedisURLRedirectService redisURLRedirectService;

    @GetMapping("/redirect")
    public ResponseEntity<Url> getRedirectedUrl(@RequestBody Url shortUrl){
        try {
            Url url = redisURLRedirectService.redirectUrl(shortUrl, true);
            if(url != null && !url.getUrl().contains("Not Mapped")) {
                return new ResponseEntity(url, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(url, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

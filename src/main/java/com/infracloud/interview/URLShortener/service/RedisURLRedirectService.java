package com.infracloud.interview.URLShortener.service;

import com.infracloud.interview.URLShortener.model.Url;

import java.util.Optional;

public interface RedisURLRedirectService {
    public Url redirectUrl(Url url, boolean isShortUrl);
}

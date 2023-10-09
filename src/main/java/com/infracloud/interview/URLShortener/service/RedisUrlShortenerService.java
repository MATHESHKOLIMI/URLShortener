package com.infracloud.interview.URLShortener.service;

import com.infracloud.interview.URLShortener.model.Url;

import java.util.Map;

public interface RedisUrlShortenerService {
    public Url shortenerUrl(Url url, boolean isShortUrl);
    public Map<String, Integer> getDomainMetrics();
}

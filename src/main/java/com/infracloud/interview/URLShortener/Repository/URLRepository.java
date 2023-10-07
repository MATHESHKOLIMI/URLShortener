package com.infracloud.interview.URLShortener.Repository;

import com.infracloud.interview.URLShortener.model.Url;

import java.util.HashMap;
import java.util.Map;

public interface URLRepository {

    public Map<Url, Url> getUrlMap();

    public Map<Url,Url> getShortUrlMap();

}

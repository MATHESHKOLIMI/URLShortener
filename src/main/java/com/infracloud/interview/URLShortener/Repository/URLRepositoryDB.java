package com.infracloud.interview.URLShortener.Repository;

import com.infracloud.interview.URLShortener.model.Url;

import java.util.List;
import java.util.Map;

public interface URLRepositoryDB {
    public String getUrl(Url url, boolean isShortUrl);


    public void saveUrl(Url url1, Url url2 , boolean isShortUrl);

    public void updateDomainMetrics(String domain);
    public Map<String, Integer> getDomainMetrics();
}

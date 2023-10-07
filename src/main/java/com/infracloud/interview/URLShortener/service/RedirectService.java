package com.infracloud.interview.URLShortener.service;

import com.infracloud.interview.URLShortener.model.Url;

import java.util.Optional;

public interface RedirectService {
    public Optional<Url> redirectUrl(Url url);
}

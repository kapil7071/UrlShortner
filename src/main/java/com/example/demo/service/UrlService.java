package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.service.model.Url;
import com.example.demo.service.model.UrlDto;

@Service
public interface UrlService {
public Url generateLink (UrlDto urlDto);
public Url persistShortLink(Url url);
public Url getEncodedUrl(String url);
public void deleteShortLink(Url url);
}

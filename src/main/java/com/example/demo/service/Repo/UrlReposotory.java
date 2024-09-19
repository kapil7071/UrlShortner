package com.example.demo.service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.service.model.Url;
@Repository
public interface UrlReposotory extends JpaRepository<Url, Long> {
public  Url findByShortLink(String shortLink); 
}

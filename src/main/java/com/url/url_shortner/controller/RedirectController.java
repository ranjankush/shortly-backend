package com.url.url_shortner.controller;

import com.url.url_shortner.model.UrlMapping;
import com.url.url_shortner.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "https://shortmedot.netlify.app")
@RestController
@AllArgsConstructor
public class RedirectController {
@Autowired
    private UrlMappingService urlMappingService;
@GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl ){
        UrlMapping urlMapping=urlMappingService.getOriginalUrl(shortUrl);
        if(urlMapping!=null){
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.add("Location",urlMapping.getOriginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}

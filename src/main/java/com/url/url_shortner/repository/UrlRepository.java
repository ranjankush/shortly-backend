package com.url.url_shortner.repository;

import com.url.url_shortner.model.UrlMapping;
import com.url.url_shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UrlRepository extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping>findByUser(User user );

}

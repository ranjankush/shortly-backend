package com.url.url_shortner.repository;

import com.url.url_shortner.dto.ClickEventDTO;
import com.url.url_shortner.model.ClickEvent;
import com.url.url_shortner.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent,Long> {
List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate,LocalDateTime endDate);
List<ClickEvent>findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings,LocalDateTime startDate,LocalDateTime endDate);

//    @Query("SELECT new com.url.url_shortner.dto.ClickStatsDTO(DATE(c.clickDate), COUNT(c)) " +
//            "FROM ClickEvent c " +
//            "WHERE c.urlMapping.user.username = :username " +
//            "GROUP BY DATE(c.clickDate) " +
//            "ORDER BY DATE(c.clickDate)")
//    List<ClickEventDTO> getClickStatsGroupedByDate(@Param("username") String username);

}

package com.url.url_shortner.controller;

import com.url.url_shortner.dto.ClickEventDTO;
import com.url.url_shortner.dto.UrlMappingDTO;
import com.url.url_shortner.model.User;
import com.url.url_shortner.service.UrlMappingService;
import com.url.url_shortner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
@Autowired
    private UrlMappingService urlMappingService;
@Autowired
private UserService userService;
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
        public ResponseEntity<?> createShortUrl(@RequestBody Map<String,String> request, Principal principal){
        String originalUrl=request.get("originalUrl");
        System.out.println(originalUrl);
        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Original URL must not be null or empty");
        }
        User user=userService.findByUsername(principal.getName());
        UrlMappingDTO urlMappingDTO=urlMappingService.createShortUrl(originalUrl,user);
        return ResponseEntity.ok(urlMappingDTO);
    }
    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?>getUserUrls(Principal principal){
        User user=userService.findByUsername(principal.getName());
        List<UrlMappingDTO>urls=urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }
    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<?>> getUrlAnalytics(
            @PathVariable String shortUrl,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        List<ClickEventDTO> clickEventDTOS = urlMappingService.getClickEventsByDate(shortUrl, startDateTime, endDateTime);
        return ResponseEntity.ok(clickEventDTOS);
    }


    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(
            Principal principal,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        User user = userService.findByUsername(principal.getName());
        Map<LocalDate, Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user, startDate, endDate);

        System.out.println(totalClicks);
        return ResponseEntity.ok(totalClicks);
    }


}

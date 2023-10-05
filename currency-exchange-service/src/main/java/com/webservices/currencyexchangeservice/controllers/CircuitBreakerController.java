package com.webservices.currencyexchangeservice.controllers;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @GetMapping("/sample-api")
    public String sampleApi() {
        log.info("Sample API call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
    }

    public String hardcodedResponse(Exception ex) {
        return "FALLBACK RESPONSE";
    }
}

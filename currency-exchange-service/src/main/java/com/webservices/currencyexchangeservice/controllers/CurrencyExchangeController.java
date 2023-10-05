package com.webservices.currencyexchangeservice.controllers;

import com.webservices.currencyexchangeservice.currencyExchange.CurrencyExchange;
import com.webservices.currencyexchangeservice.currencyExchange.CurrencyExchangeRepository;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CurrencyExchangeController {

    private Environment environment;
    private CurrencyExchangeRepository repository;

    @Retry(name = "currency-exchange", fallbackMethod = "hardcodedResponse")
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@Valid @PathVariable String from, @Valid @PathVariable String to) {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        return currencyExchange;
    }
}

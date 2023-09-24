package com.webservices.currencyconversionservice.controllers;

import com.webservices.currencyconversionservice.currencyConversion.CurrencyConversion;
import com.webservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(Objects.requireNonNull(currencyConversion).getId(),
                from, to, currencyConversion.getConversionMultiple(),
                quantity, quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }
}

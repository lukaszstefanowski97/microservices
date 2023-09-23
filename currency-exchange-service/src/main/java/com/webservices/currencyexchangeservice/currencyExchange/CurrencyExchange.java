package com.webservices.currencyexchangeservice.currencyExchange;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity(name = "currency_exchange")
@NoArgsConstructor
public class CurrencyExchange {

    public CurrencyExchange(String from, String to, BigDecimal conversionMultiple) {
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    @Id
    @GeneratedValue
    private Long id;


    @Size(min = 2)
    @Column(name = "currency_from")
    private String from;

    @Size(min = 2)
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;
}

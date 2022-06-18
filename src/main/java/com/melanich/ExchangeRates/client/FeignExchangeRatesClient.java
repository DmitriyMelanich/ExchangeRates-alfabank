package com.melanich.ExchangeRates.client;

import com.melanich.ExchangeRates.model.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OERClient", url = "${openexchangerates.url.general}")
public interface FeignExchangeRatesClient {
    @GetMapping("/latest.json")
    public ExchangeRates getLatestExchangeRates (
        @RequestParam("app_id") String app_id
    );

    @GetMapping("/historical/{date}.json")
    public ExchangeRates getHistoricalExchangeRates (
            @PathVariable String date,
            @RequestParam("app_id") String appId
    );
}

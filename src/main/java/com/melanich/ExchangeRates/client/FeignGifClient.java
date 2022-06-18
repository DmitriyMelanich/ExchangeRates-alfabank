package com.melanich.ExchangeRates.client;

import com.melanich.ExchangeRates.model.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FeignClient",url = "${gif.url.general}")
public interface FeignGifClient {
    @GetMapping("/random")
    public Gif getGif(
            @RequestParam("api_key") String apiKey,
            @RequestParam("tag") String tag
    );
}
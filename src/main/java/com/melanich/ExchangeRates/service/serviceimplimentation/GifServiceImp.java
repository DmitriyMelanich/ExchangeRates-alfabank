package com.melanich.ExchangeRates.service.serviceimplimentation;

import com.melanich.ExchangeRates.client.FeignGifClient;
import com.melanich.ExchangeRates.model.Gif;
import com.melanich.ExchangeRates.service.serviceinterface.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifServiceImp implements GifService {

    private FeignGifClient gifClient;

    @Value("${gif.url.id}")
    private String apiKey;

    @Autowired
    public GifServiceImp(FeignGifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Override
    public Gif getGif(String tag) {
        return gifClient.getGif(apiKey, tag);
    }
}

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

    @Value("${gif.tag.rich}")
    private String rich;

    @Value("${gif.tag.broke}")
    private String broke;

    @Value("${gif.url.id}")
    private String apiKey;

    @Autowired
    public GifServiceImp(FeignGifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Override
    public String getUrlGif(double d) {

        Gif result = null;

        if (d >= 0) {
            result = gifClient.getGif(apiKey, rich);
        } else {
            result = gifClient.getGif(apiKey, broke);
        }
        String[] arrayStr = result.getData().getEmbed_url().split("/");
        String gifName = arrayStr[arrayStr.length - 1];
        StringBuilder stringBuilder = new StringBuilder("https://media2.giphy.com/media/")
                .append(gifName)
                .append("/giphy.gif");
        return stringBuilder.toString();
    }
}
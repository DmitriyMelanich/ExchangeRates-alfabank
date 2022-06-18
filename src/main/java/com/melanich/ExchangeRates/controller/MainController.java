package com.melanich.ExchangeRates.controller;

import com.melanich.ExchangeRates.model.Gif;
import com.melanich.ExchangeRates.service.serviceinterface.ExchangeRatesService;
import com.melanich.ExchangeRates.service.serviceinterface.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping()
public class MainController {

    private ExchangeRatesService ratesService;

    private GifService gifService;

    @Value("${gif.tag.rich}")
    private String rich;

    @Value("${gif.tag.broke}")
    private String broke;

    @Autowired
    public MainController(ExchangeRatesService ratesService, GifService gifService) {
        this.ratesService = ratesService;
        this.gifService = gifService;
    }

    @GetMapping(value = "/result", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<byte[]> getResult() throws IOException, URISyntaxException, InterruptedException {
        Gif result = null;
        double equalsResult = ratesService.equalsRates();
        if (equalsResult>=0){
            result = gifService.getGif(this.rich);
        } else {
            result = gifService.getGif(this.broke);
        }

        String[] arrayStr = result.getData().getEmbed_url().split("/");
        String gifName = arrayStr[arrayStr.length-1];
        StringBuilder stringBuilder = new StringBuilder("https://media2.giphy.com/media/")
                .append(gifName)
                .append("/giphy.gif");
        String gifStr = stringBuilder.toString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(gifStr))
                .GET()
                .build();

        HttpResponse<byte[]> response =
                client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(response.body());
    }

}
package com.melanich.ExchangeRates.controller;

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
        double equalsResult = ratesService.equalsRates();
        String urlGif = gifService.getUrlGif(equalsResult);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlGif))
                .GET()
                .build();
        HttpResponse<byte[]> response =
                null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(response.body());
    }
}
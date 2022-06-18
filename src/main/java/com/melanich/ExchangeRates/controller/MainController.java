package com.melanich.ExchangeRates.controller;

import com.melanich.ExchangeRates.model.Data;
import com.melanich.ExchangeRates.model.Gif;
import com.melanich.ExchangeRates.service.serviceinterface.ExchangeRatesService;
import com.melanich.ExchangeRates.service.serviceinterface.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

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

    @GetMapping("/result")
    public Data getResult() {
        Gif result = null;
        double equalsResult = ratesService.equalsRates();
        if (equalsResult>=0){
            result = gifService.getGif(this.rich);
        } else {
            result = gifService.getGif(this.broke);
        }
        return result.getData();
    }
}

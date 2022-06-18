package com.melanich.ExchangeRates.service.serviceinterface;

import com.melanich.ExchangeRates.model.Gif;
import org.springframework.http.ResponseEntity;

public interface GifService {

    public String getUrlGif(double d);

}

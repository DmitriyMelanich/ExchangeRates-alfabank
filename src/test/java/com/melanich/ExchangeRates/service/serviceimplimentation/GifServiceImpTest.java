package com.melanich.ExchangeRates.service.serviceimplimentation;

import com.melanich.ExchangeRates.client.FeignGifClient;
import com.melanich.ExchangeRates.model.Gif;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GifServiceImpTest {

    @Autowired
    GifServiceImp gifService;

    @MockBean
    FeignGifClient gifClient;

    @Test
    void getGifNotNull() {
        Gif gif = new Gif();
        Mockito.when(gifClient.getGif(anyString(),anyString())).thenReturn(gif);
        assertNotNull(gifService.getGif("rich"));
    }
}
package com.melanich.ExchangeRates.service.serviceimplimentation;

import com.melanich.ExchangeRates.client.FeignGifClient;
import com.melanich.ExchangeRates.model.Data;
import com.melanich.ExchangeRates.model.Gif;
import org.junit.jupiter.api.BeforeEach;
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

    String str;
    Gif gif;
    @BeforeEach
    void init() {
        str = "https://media2.giphy.com/media/123/giphy.gif";
        Data data = new Data();
        data.setEmbed_url("http://giphy.com/embed/123");
        gif = new Gif();
        gif.setData(data);
    }

    @Test
    void getGifTest() {
        Mockito.when(gifClient.getGif(anyString(),anyString())).thenReturn(gif);
        assertEquals("https://media2.giphy.com/media/123/giphy.gif",gifService.getUrlGif(1.0));
    }
}
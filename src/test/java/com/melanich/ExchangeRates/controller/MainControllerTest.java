package com.melanich.ExchangeRates.controller;

import com.melanich.ExchangeRates.model.Data;
import com.melanich.ExchangeRates.model.Gif;
import com.melanich.ExchangeRates.service.serviceinterface.ExchangeRatesService;
import com.melanich.ExchangeRates.service.serviceinterface.GifService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
class MainControllerTest {
    @MockBean
    private ExchangeRatesService ratesService;
    @MockBean
    private GifService gifService;
    @Autowired
    MockMvc mockMvc;
    Gif gif;

    @BeforeEach
     void init() {
        gif = new Gif();
        Data testData = new Data(
                "Uv35cZxoaHHmc03z3d",
                "Corona Atm GIF by INTO ACT!ON",
                "g",
                "https://giphy.com/embed/Uv35cZxoaHHmc03z3d",
                "https://giphy.com/gifs/IntoAction-covid19-covid-19-Uv35cZxoaHHmc03z3d"
        );
        gif.setData(testData);
        Mockito.when(ratesService.equalsRates()).thenReturn(1.0);
        Mockito.when(gifService.getGif("rich")).thenReturn(gif);
    }

    @Test
    void getResultTest() throws Exception {
        mockMvc.perform(get("/result"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.IMAGE_GIF_VALUE));
    }
}
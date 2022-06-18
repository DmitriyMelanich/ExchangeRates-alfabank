package com.melanich.ExchangeRates.service.serviceimplimentation;

import com.melanich.ExchangeRates.client.FeignExchangeRatesClient;
import com.melanich.ExchangeRates.model.ExchangeRates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExchangeRatesServiceImpTest {

    @Autowired
    private ExchangeRatesServiceImp ratesService;

    @MockBean
    private FeignExchangeRatesClient exchangeRatesClient;

    private static ExchangeRates currentRates;

    private static ExchangeRates prevRates;

    @BeforeAll
    public static void init() {
        currentRates= new ExchangeRates();
        currentRates.setTimestamp(1655403700840L);
        Map<String,Double> rates = new HashMap<>();
        rates.put("RUB",70.00);
        currentRates.setRates(rates);

        prevRates= new ExchangeRates();
        prevRates.setTimestamp(1655317300840L);
        Map<String,Double> rates2 = new HashMap<>();
        rates2.put("RUB",60.00);
        prevRates.setRates(rates2);
    }

    @Test
    public void equalsRatesShouldTrue() {

        Mockito.when(exchangeRatesClient.getLatestExchangeRates(anyString())).thenReturn(currentRates);
        Mockito.when(exchangeRatesClient.getHistoricalExchangeRates(anyString(),anyString())).thenReturn(prevRates);

        assertTrue(ratesService.equalsRates()>0);
    }
}

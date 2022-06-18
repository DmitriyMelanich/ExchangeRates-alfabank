package com.melanich.ExchangeRates.service.serviceimplimentation;

import com.melanich.ExchangeRates.client.FeignExchangeRatesClient;
import com.melanich.ExchangeRates.model.ExchangeRates;
import com.melanich.ExchangeRates.service.serviceinterface.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Service
public class ExchangeRatesServiceImp implements ExchangeRatesService {

    private FeignExchangeRatesClient feignClient;

    private ExchangeRates currentRates;

    private ExchangeRates prevRates;

    @Value("${openexchangerates.app.id}")
    private String appId;

    @Value("${openexchangerates.base}")
    private String base;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    public ExchangeRatesServiceImp(FeignExchangeRatesClient feignClient) {
        this.feignClient = feignClient;
    }

    public void refreshRates() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String currentDate = formatter.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String prevDate = formatter.format(calendar.getTime());
        if (prevRates == null || !formatter.format(prevRates.getTimestamp() * 1000).equals(prevDate)) {
            prevRates = feignClient.getHistoricalExchangeRates(prevDate, appId);
        }
        if (currentRates == null || !formatter.format(currentRates.getTimestamp() * 1000).equals(currentDate)) {
            currentRates = feignClient.getLatestExchangeRates(appId);
        }
    }

    public double getBaseValue(ExchangeRates exchangeRates){
        Map<String,Double> rates = exchangeRates.getRates();
        double value = rates.get(base);
        return value;
    }

    @Override
    public double equalsRates() {
        refreshRates();
        return getBaseValue(currentRates)-getBaseValue(prevRates);
    }
}

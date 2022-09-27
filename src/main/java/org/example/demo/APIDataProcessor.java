package org.example.demo;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Class implements request processing.
 * 
 * @author M Ahmed Shaikh
 *
 */
@Service
public class APIDataProcessor {

    @Value("${CoinAPI.host}")
    private String COIN_API;
    @Value("${CurrencyAPI.host}")
    private String CURRENCY_API;
    @Value("${Coin.type}")
    private String COIN_TYPE;
    @Value("${Base.currencies}")
    private String CURRENCIES;
    private static String BASE_CURRENCY = "USD";

    private static final Logger logger = LoggerFactory.getLogger(DigitalCoinDetail.class);

    @Autowired
    DigitalCoinService service;

    @Autowired
    DigitalCoinDetail digitalCoinDetail;

    /**
     * This method fetch data from coins API.
     * 
     * @return String coin details.
     * @throws ConnectException   API out of reach.
     * @throws URISyntaxException String parse issue.
     */
    public String getRequestForCoinDetail(String currency) throws URISyntaxException, ConnectException {

        HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).followRedirects(Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(COIN_API + currency)).GET().build();
        String response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join()
            .toString();
        logger.info("Coins API successfully fetched");
        return response;
    }

    /**
     * This method get converted currency from currency API.
     * 
     * @return Map of currencies.
     * @throws JSONException           Json parsing and serialization.
     * @throws ConnectException        API out of reach.
     * @throws URISyntaxException      String parse issue.
     * @throws JsonProcessingException Json parsing and serialization.
     */
    public String getConvertedCurrency(String localCurrency, BigDecimal priceUSD)
    throws NumberFormatException, URISyntaxException, JsonProcessingException {
        String url = CURRENCY_API + BASE_CURRENCY + "&to=" + localCurrency + "&amount=" + priceUSD;
        HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).followRedirects(Redirect.NORMAL).build();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
        String response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).join()
            .toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode result = mapper.readTree(response).get("result");
        logger.info("Currency API successfully fetched");
        return result.asText();
    }

    /**
     * This method Scheduled for 30sec and insert all coins details in DB.
     * 
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * 
     * @throws NumberFormatException   If currency not found.
     * @throws URISyntaxException      String parse issue.
     * @throws JsonProcessingException Json parsing and serialization.
     * @throws ConnectException        API out of reach.
     * 
     */
    @Scheduled(fixedRate = 30000)
    public void processAllCurrencies() {
        service.createTable();

        try {
            List < String > currencies = Arrays.asList(CURRENCIES.split("\\s*,\\s*"));
            for (String currency: currencies) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode coinObj = mapper.readTree(getRequestForCoinDetail(COIN_TYPE)).get("data");
                BigDecimal basePrice = BigDecimal.valueOf(Double.parseDouble(coinObj.get("priceUsd").asText()));
                BigDecimal localPrice = BigDecimal
                    .valueOf(Double.parseDouble(getConvertedCurrency(currency, basePrice)));
                digitalCoinDetail.setId(coinObj.get("id").asText());
                digitalCoinDetail.setName(coinObj.get("name").asText());
                digitalCoinDetail.setBaseCurrency(BASE_CURRENCY);
                digitalCoinDetail.setBasePrice(basePrice);
                digitalCoinDetail.setLocalCurrency(currency);
                digitalCoinDetail.setLocalprice(localPrice);
                service.insertDetails(digitalCoinDetail);

            }
        } catch (URISyntaxException | JsonProcessingException | NumberFormatException | ConnectException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
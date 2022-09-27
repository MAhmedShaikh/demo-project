package org.example.demo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * This class implements test for project.
 *
 * @author M Ahmed Shaikh
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DemoProjectApplicationTests {

    @Autowired
    DigitalCoinService digitalCoinService;

    @Test
    @Order(1)
    public void shouldInsertDigitalCoinDetail() {
        DigitalCoinDetail digitalCoinDetail = new DigitalCoinDetail();
        digitalCoinDetail.setId("bitcoin");
        digitalCoinDetail.setName("Bitcoin");
        digitalCoinDetail.setBaseCurrency("USD");
        digitalCoinDetail.setLocalprice(BigDecimal.ONE);
        digitalCoinDetail.setLocalCurrency("PKR");
        digitalCoinDetail.setLocalprice(BigDecimal.ONE);

        int rows = digitalCoinService.insertDetails(digitalCoinDetail);
        Assertions.assertNotNull(rows > 0);
    }

    @Test
    @Order(2)
    public void shouldFindByLocalCurrency() {
        List < DigitalCoinDetail > digitalCoinDetails = digitalCoinService.findAllByLocalCurrency("PKR");
        Assertions.assertTrue(!digitalCoinDetails.isEmpty());
    }

    @Test
    @Order(3)
    public void shouldFindByAll() {
        List < DigitalCoinDetail > digitalCoinDetails = digitalCoinService.findAll();
        Assertions.assertTrue(!digitalCoinDetails.isEmpty());
    }

    @Test
    @Order(4)
    public void shouldDeleteDigitalCoinDetail() {
        DigitalCoinDetail digitalCoinDetail = new DigitalCoinDetail();
        digitalCoinDetail.setId("bitcoin");
        digitalCoinDetail.setName("Bitcoin");
        digitalCoinDetail.setBaseCurrency("USD");
        digitalCoinDetail.setBasePrice(BigDecimal.ONE);
        digitalCoinDetail.setLocalCurrency("PKR");
        digitalCoinDetail.setLocalprice(BigDecimal.ONE);
        digitalCoinService.insertDetails(digitalCoinDetail);
        digitalCoinService.deleteByLocalCurrency("PKR");
        List < DigitalCoinDetail > list = digitalCoinService.findAllByLocalCurrency("PKR");
        Assertions.assertTrue(list.isEmpty());

    }

}
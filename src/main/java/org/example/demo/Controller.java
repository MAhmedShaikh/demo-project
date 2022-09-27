package org.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class implements Rest operations.
 * 
 * @author M Ahmed Shaikh
 *
 */
@RestController
@RequestMapping("/digital-currency")
public class Controller {

    @Autowired
    DigitalCoinService service;

    /**
     * This method find all records.
     * 
     * @return list of objects.
     */
    @GetMapping()
    public List < DigitalCoinDetail > getAllCoinDetails() {
        return service.findAll();
    }

    /**
     * This method find records by local currency.
     * 
     * @param currency this is local currency.
     * @return List of objects.
     */
    @GetMapping("/local-currency")
    public List < DigitalCoinDetail > getAllCoinDetailsByLocalCurrency(@RequestParam String currency) {
        return service.findAllByLocalCurrency(currency);
    }

    /**
     * This method delete records by currency.
     * 
     * @param currency this is local currency.
     * @return String messages.
     */
    @DeleteMapping("/local-currency")
    public ResponseEntity<?> deleteByLocalCurrency(@RequestParam String currency) {
        int res = service.deleteByLocalCurrency(currency);
        return res == 0 ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

}
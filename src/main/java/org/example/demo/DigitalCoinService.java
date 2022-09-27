package org.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements business logics.
 * 
 * @author M Ahmed Shaikh
 *
 */
@Service
public class DigitalCoinService {

    @Autowired
    DigitalCoinRepositoryDAO dao;

    /**
     * TODO:This method for create table.
     */
    public void createTable() {
        try {
            dao.createTable();
        } catch (RuntimeException e) {}
    }


    /**
     * This methods insert records;
     * 
     * @param currencyModel object of coins.
     * @return int value  number of rows.
     */
    public int insertDetails(DigitalCoinDetail currencyModel) {
        return dao.insertRecord(currencyModel);
    }

    /**
     * This method find all coin details. 
     * 
     * @return List of digital coin details;
     */
    public List < DigitalCoinDetail > findAll() {
        return dao.findAll();
    }

    /**
     * This method find coin detail by local currency.
     * 
     * @param localCurrency this is local currency.
     * @return List of coins Details.
     */
    public List < DigitalCoinDetail > findAllByLocalCurrency(String localCurrency) {
        return dao.findAllByLocalCurrency(localCurrency);
    }

    /**
     * This method delete all coins detail by local currency.
     * 
     * @param localCurrency this is local Currency.
     * @return int  delete flag.
     */
    public int deleteByLocalCurrency(String localCurrency) {
        return dao.deleteByLocalCurrency(localCurrency);
    }

}
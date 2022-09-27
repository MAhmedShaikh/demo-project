package org.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * This class implements database operations.
 * 
 * @author M Ahmed Shaikh
 *
 */
@Repository
public class DigitalCoinRepositoryDAO {

    private static final Logger logger = LoggerFactory.getLogger(DigitalCoinDetail.class);
    private static String CREATE_TABLE = "CREATE TABLE coin_details (id VARCHAR(255),name VARCHAR(255),base_currency VARCHAR(255),base_price VARCHAR(255),local_currency VARCHAR(255),local_price VARCHAR(255),time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    private static String INSERT = "INSERT INTO coin_details(id,name,base_currency,base_price,local_currency,local_price) Values(?,?,?,?,?,?)";
    private static String FIND_ALL = "SELECT id,name,base_currency,base_price,local_currency,local_price,time_stamp FROM coin_details";
    private static String FIND_ALL_BY_LOCAL = "SELECT id,name,base_currency,base_price,local_currency,local_price,time_stamp FROM coin_details where local_currency = ?";
    private static String DELETE_ALL_BY_LOCAL = "DELETE FROM coin_details WHERE local_currency = ?";

    @Autowired
    JdbcTemplate template;


    /**
     * TODO:This method is create table for coins details;
     * 
     */
    public void createTable() throws RuntimeException {
        template.execute(CREATE_TABLE);
        logger.info("Table created");
    }


    /**
     * This method insert records.
     * 
     * @param cd currency object.
     */
    public int insertRecord(DigitalCoinDetail cd) {
        return template.update(INSERT, cd.getId(),
            cd.getName(), cd.getBaseCurrency(), cd.getBasePrice(),
            cd.getLocalCurrency(), cd.getLocalprice());
    }

    /**
     * This method find all records.
     * 
     * @return List of objects.
     */
    public List < DigitalCoinDetail > findAll() {
        return template.query(FIND_ALL, new DigitalCoinRowMapper());
    }

    /**
     * This method find records by currency.
     * 
     * @param local this local currency.
     * @return List of objects.
     */
    public List < DigitalCoinDetail > findAllByLocalCurrency(String localCurrency) {
        return template.query(FIND_ALL_BY_LOCAL, new DigitalCoinRowMapper(), localCurrency);
    }

    /**
     * This method delete all records by currency.
     * 
     * @param local this local currency.
     * @return int value;
     */
    public int deleteByLocalCurrency(String localCurrency) {
        return template.update(DELETE_ALL_BY_LOCAL, localCurrency);
    }

}
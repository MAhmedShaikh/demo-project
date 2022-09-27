package org.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * This class use for rowMapper.
 * @author M Ahmed Shaikh
 *
 */
@Component
public class DigitalCoinRowMapper implements RowMapper < DigitalCoinDetail > {

    @Override
    public DigitalCoinDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        DigitalCoinDetail digitalCoinDetail = new DigitalCoinDetail();
        digitalCoinDetail.setId(rs.getString(1));
        digitalCoinDetail.setName(rs.getString(2));
        digitalCoinDetail.setBaseCurrency(rs.getString(3));
        digitalCoinDetail.setBasePrice(rs.getBigDecimal(4));
        digitalCoinDetail.setLocalCurrency(rs.getString(5));
        digitalCoinDetail.setLocalprice(rs.getBigDecimal(6));
        digitalCoinDetail.setTimeStamp(rs.getTimestamp(7));
        return digitalCoinDetail;
    }


}
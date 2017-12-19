package com.portal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by FGZ on 2017/12/6.
 */
@Entity
@Table(name = "m4_stock_query_info")
public class StockQueryInfo implements Serializable {
    private static final long serialVersionUID = 1908578954568146722L;

    private Integer id;
    private String userId;
    private Date time;
    private String symbol;
    private String stockName;

    public StockQueryInfo() {
    }

    public StockQueryInfo(Integer id, String userId, Date time, String symbol, String stockName) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.symbol = symbol;
        this.stockName = stockName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name = "stockname")
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}

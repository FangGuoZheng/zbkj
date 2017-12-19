package com.portal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by FGZ on 2017/12/6.
 */
@Entity
@Table(name = "m4_indicator_query_info")
public class IndicatorQueryInfo implements Serializable {
    private static final long serialVersionUID = 4512546082948146721L;

    private Integer id;
    private String userId;
    private Date time;
    private String symbol;
    private String stockName;
    private String indicatorName;

    public IndicatorQueryInfo() {
    }

    public IndicatorQueryInfo(Integer id, String userId, Date time, String symbol, String stockName, String indicatorName) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.symbol = symbol;
        this.stockName = stockName;
        this.indicatorName = indicatorName;
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

    @Column(name = "indicatorname")
    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }
}

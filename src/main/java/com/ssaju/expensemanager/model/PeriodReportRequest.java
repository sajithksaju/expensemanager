package com.ssaju.expensemanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Created by ssaju on 2/7/17.
 */
public class PeriodReportRequest {

    private String paymentType;
    private Integer month;
    private Integer year;
    private LocalDate startDate;
    private LocalDate endDate;

    @JsonCreator
    public PeriodReportRequest(@JsonProperty("paymentType") String paymentType,
                               @JsonProperty("month") Integer month,
                               @JsonProperty("year") Integer year,
                               @JsonProperty("startDate") LocalDate startDate,
                               @JsonProperty("endDate") LocalDate endDate) {
        this.paymentType = paymentType;
        this.month = month;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }
}

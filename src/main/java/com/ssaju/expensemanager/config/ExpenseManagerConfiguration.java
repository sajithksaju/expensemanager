package com.ssaju.expensemanager.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssaju.expensemanager.service.ExpenseService;
import com.ssaju.expensemanager.service.PaymentMethodService;
import com.ssaju.expensemanager.service.ReportService;
import com.ssaju.expensemanager.util.ExpenseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * Created by ssaju on 2/4/17.
 */
@Configuration
public class ExpenseManagerConfiguration {

    @Bean("objectMapper")
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper;
    }

    @Bean("paymentMethodService")
    public PaymentMethodService paymentMethodService() {
        return new PaymentMethodService();
    }

    @Bean("expenseService")
    public ExpenseService expenseService(){
        return new ExpenseService();
    }

    @Bean("expenseUtil")
    public ExpenseUtil expenseUtil(){ return new ExpenseUtil();}

    @Bean("reportService")
    public ReportService reportService(){ return new ReportService();}

}

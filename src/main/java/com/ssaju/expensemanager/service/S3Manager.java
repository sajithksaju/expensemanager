package com.ssaju.expensemanager.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssaju.expensemanager.model.Expenses;
import com.ssaju.expensemanager.model.PaymentMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by ssaju on 2/8/17.
 */
public class S3Manager {

    @Autowired
    AmazonS3Client amazonS3Client;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${em.s3.paymentmethods.file.name}")
    private String paymentMethodsFileName;


    @Value("${em.s3.expenses.file.name}")
    private String expensesFileName;

    @Value("${em.s3.foldername}")
    private String s3FolderName;

    private S3Object getS3Object(String fileName) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, fileName);
        S3Object s3Object = amazonS3Client.getObject(getObjectRequest);
        return s3Object;
    }

    public Expenses getExpensesFromS3() {
        Expenses expenses = new Expenses();
        try {
            S3Object s3Object = getS3Object(s3FolderName + expensesFileName);
            expenses = objectMapper.readValue(s3Object.getObjectContent(), Expenses.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return expenses;
    }

    public PaymentMethods getPaymentMethodsFromS3() {
        PaymentMethods paymentMethods = new PaymentMethods();
        try {
            S3Object s3Object = getS3Object(s3FolderName + paymentMethodsFileName);
            paymentMethods = objectMapper.readValue(s3Object.getObjectContent(), PaymentMethods.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paymentMethods;
    }

    public void updateExpensesS3File(Expenses expenses){
        updateFileInS3(expenses, expensesFileName);
    }

    public void updatepaymentMethodsS3File(PaymentMethods paymentMethods){
        updateFileInS3(paymentMethods, paymentMethodsFileName);
    }

    public void updateFileInS3(Object object, String fileName){
        try {
            String jsonString= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            InputStream jsonIs= new ByteArrayInputStream(jsonString.getBytes(Charset.forName("UTF-8")));
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket,s3FolderName+fileName, jsonIs, new ObjectMetadata());
            amazonS3Client.putObject(putObjectRequest);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

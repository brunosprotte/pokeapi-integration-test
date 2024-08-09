package com.poke.api.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Log4j2
@Configuration
@RequiredArgsConstructor
@Profile("!integration-test")
public class SQSConfig {

    @Value("${cloud.region}")
    private String region;

    @Bean
    @Primary
    public AmazonSQS defaultAmazonSQSClient() {
        log.traceEntry("defaultAmazonSQSClient()");

        return log.traceExit(
                "defaultAmazonSQSClient(): {}",
                AmazonSQSClientBuilder.standard()
                        .withCredentials(
                                new DefaultAWSCredentialsProviderChain())
                        .withRegion(Regions.fromName(region))
                        .build()
        );
    }
}

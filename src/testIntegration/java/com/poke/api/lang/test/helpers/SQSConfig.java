package com.poke.api.lang.test.helpers;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@TestConfiguration(proxyBeanMethods = false)
@Profile("integration-test")
public class SQSConfig {

    @Value("${cloud.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.credentials.secretKey}")
    private String secretKey;
    @Value("${spring.cloud.aws.region}")
    private String region;
    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    @Bean
    @DependsOn("localStackContainer")
    public AmazonSQS defaultAmazonSQSAsync() {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey))
                )
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsEndpoint, region)
                )
                .build();
    }
}

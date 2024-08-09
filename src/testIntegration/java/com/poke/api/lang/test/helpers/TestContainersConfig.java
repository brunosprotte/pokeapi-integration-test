package com.poke.api.lang.test.helpers;

import com.poke.api.lang.test.AbstractIT;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfig {

    @Bean("localStackContainer")
    public LocalStackContainer localStackContainer() {
        LocalStackContainer container = new LocalStackContainer(DockerImageName.parse("localstack/localstack:3.1.0")).withEnv("SKIP_SSL_CERT_DOWNLOAD", "true").withServices(SQS);

        container.start();

        System.setProperty("cloud.credentials.accessKey", container.getAccessKey());
        System.setProperty("cloud.credentials.secretKey", container.getSecretKey());

        System.setProperty("AWS_ACCESS_KEY_ID", container.getAccessKey());
        System.setProperty("AWS_SECRET_KEY", container.getSecretKey());
        System.setProperty("spring.cloud.aws.endpoint", "http://localhost:4566");

        System.setProperty("spring.cloud.aws.sqs.endpoint", container.getEndpointOverride(SQS).toString());

        System.setProperty("cloud.aws.sqs.envio.queue.url", container.getEndpoint().toString() + "/000000000000/" + AbstractIT.SQS_QUEUE);

        System.setProperty("spring.cloud.aws.sqs.endpoint", container.getEndpointOverride(LocalStackContainer.Service.SQS).toString());

        System.setProperty("spring.cloud.aws.region", "us-east-1");
        System.setProperty("cloud.region", "us-east-1");

        return container;
    }

    @Bean("postgreSQLContainer")
    @ServiceConnection
    public PostgreSQLContainer postgreSQLContainer() {
        PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:14.4").withUsername("postgres").withPassword("postgres123").withDatabaseName("pokeapi-test");
        postgreSQLContainer.start();

        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());

        return postgreSQLContainer;
    }
}

package com.poke.api.lang.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poke.api.Application;
import com.poke.api.lang.test.extension.IntegrationTestExtension;
import com.poke.api.lang.test.helpers.SQSConfig;
import com.poke.api.lang.test.helpers.WebSecurityConfig;
import com.poke.api.lang.test.util.WireMockUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.localstack.LocalStackContainer;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@ExtendWith(IntegrationTestExtension.class)
@ImportAutoConfiguration(
        classes = {
                SQSConfig.class,
                WebSecurityConfig.class
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "cloud.aws.region.static=us-east-1",
                "app.security.custom.enabled=true",
        })
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.poke.api.lang", "com.poke.api"})
@ActiveProfiles("integration-test")
public abstract class AbstractIT {

    public static final String LOCALSTACK_URL = "http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/";
    public static final String SQS_QUEUE = "SQSQueue";

    public static boolean configsDone = false;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @LocalServerPort
    protected int port;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected LocalStackContainer localStackContainer;

    @BeforeAll
    static void setupAll(@Autowired LocalStackContainer localStackContainer) throws IOException, InterruptedException {

        localStackContainer.execInContainer(
                "awslocal", "sqs", "create-queue", "--queue-name", SQS_QUEUE
        );

        if (!configsDone) {
            configsDone = true;
            WireMockUtil wireMockUtil = new WireMockUtil(localStackContainer);
        }
    }

    @BeforeEach
    public void setupEach(@Autowired LocalStackContainer localStackContainer) throws IOException, InterruptedException {
        localStackContainer.execInContainer(
                "awslocal", "sqs", "purge-queue", "--queue-url", LOCALSTACK_URL + SQS_QUEUE
        );

        RestAssured.port = this.port;
    }

    protected String receiveMessageFromQueue(final String queue) throws IOException, InterruptedException {

        Container.ExecResult messages = localStackContainer.execInContainer(
                "awslocal",
                "sqs",
                "receive-message",
                "--queue-url",
                LOCALSTACK_URL + queue
        );

        if (messages.getStderr().isEmpty()) {
            return messages.getStdout();
        } else {
            return messages.getStderr();
        }
    }

    protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

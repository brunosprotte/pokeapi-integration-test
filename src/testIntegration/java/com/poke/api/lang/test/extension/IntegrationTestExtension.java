package com.poke.api.lang.test.extension;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.poke.api.lang.test.helpers.TestContainersConfig;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

@Testcontainers
@ImportAutoConfiguration(classes = {
        TestContainersConfig.class
})
public class IntegrationTestExtension implements BeforeAllCallback, BeforeEachCallback {

    static final Lock lock = new ReentrantLock();

    private static boolean started = false;

    @Autowired
    LocalStackContainer localStackContainer;

    WireMockClassRule wireMockClassRule;

    @Override
    public void beforeAll(ExtensionContext context) {
        lock.lock();
        if (!started) {
            started = true;

            this.wireMockClassRule = new WireMockClassRule(wireMockConfig().port(9874));
            this.wireMockClassRule.start();
            WireMock.configureFor("localhost", this.wireMockClassRule.port());
            System.setProperty(
                    "app.wiremock.endpoint",
                    format("http://localhost:%s", this.wireMockClassRule.port()));
            context.getRoot().getStore(GLOBAL).put("INTEGRATION_TEST_wiremock", this.wireMockClassRule);

            System.setProperty("app.security.custom.enabled", "true");

            lock.unlock();
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        this.wireMockClassRule = (WireMockClassRule) context.getRoot().getStore(GLOBAL).get("INTEGRATION_TEST_wiremock");
        this.wireMockClassRule.resetAll();
    }
}

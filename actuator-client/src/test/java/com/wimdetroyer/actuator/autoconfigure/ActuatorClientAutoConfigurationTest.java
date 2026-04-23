package com.wimdetroyer.actuator.autoconfigure;

import com.wimdetroyer.actuator.client.ActuatorClient;
import com.wimdetroyer.actuator.client.ActuatorClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class ActuatorClientAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ActuatorClientAutoConfiguration.class));

    @Test
    void autoConfiguration_shouldCreateFactoryWithDefaultProperties() {
        contextRunner.run(context -> assertThat(context)
                .hasSingleBean(ActuatorClientFactory.class)
                .doesNotHaveBean(ActuatorClient.class));
    }

    @Test
    void autoConfiguration_shouldCreateClientWhenBaseUrlIsConfigured() {
        contextRunner
                .withPropertyValues("actuator.client.base-url=http://localhost:8080")
                .run(context -> assertThat(context)
                        .hasSingleBean(ActuatorClientFactory.class)
                        .hasSingleBean(ActuatorClient.class));
    }
}

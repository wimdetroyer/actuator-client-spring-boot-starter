package com.wimdetroyer.actuator.autoconfigure;

import com.wimdetroyer.actuator.client.ActuatorClient;
import com.wimdetroyer.actuator.client.ActuatorClientFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Auto-configuration for the Actuator Client.
 */
@AutoConfiguration
@EnableConfigurationProperties(ActuatorClientProperties.class)
public class ActuatorClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ActuatorClientFactory actuatorClientFactory(ActuatorClientProperties properties) {
        return new ActuatorClientFactory(properties.connectTimeout(), properties.readTimeout());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty("actuator.client.base-url")
    public ActuatorClient actuatorClient(ActuatorClientFactory factory, ActuatorClientProperties properties) {
        return factory.forUrl(properties.baseUrl(), properties.actuatorPath());
    }

}

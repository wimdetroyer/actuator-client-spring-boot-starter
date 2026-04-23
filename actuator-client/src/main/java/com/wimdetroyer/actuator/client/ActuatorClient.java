package com.wimdetroyer.actuator.client;

import com.wimdetroyer.actuator.endpoints.beans.Beans;
import com.wimdetroyer.actuator.endpoints.caches.Caches;
import com.wimdetroyer.actuator.endpoints.conditions.Conditions;
import com.wimdetroyer.actuator.endpoints.configprops.ConfigProps;
import com.wimdetroyer.actuator.endpoints.env.Env;
import com.wimdetroyer.actuator.endpoints.flyway.Flyway;
import com.wimdetroyer.actuator.endpoints.health.Health;
import com.wimdetroyer.actuator.endpoints.heapdump.HeapDump;
import com.wimdetroyer.actuator.endpoints.httpexchanges.HttpExchanges;
import com.wimdetroyer.actuator.endpoints.info.Info;
import com.wimdetroyer.actuator.endpoints.integrationgraph.IntegrationGraph;
import com.wimdetroyer.actuator.endpoints.liquibase.Liquibase;
import com.wimdetroyer.actuator.endpoints.logfile.LogFile;
import com.wimdetroyer.actuator.endpoints.loggers.Loggers;
import com.wimdetroyer.actuator.endpoints.mappings.Mappings;
import com.wimdetroyer.actuator.endpoints.metrics.Metrics;
import com.wimdetroyer.actuator.endpoints.prometheus.Prometheus;
import com.wimdetroyer.actuator.endpoints.quartz.Quartz;
import com.wimdetroyer.actuator.endpoints.sbom.Sbom;
import com.wimdetroyer.actuator.endpoints.scheduledtasks.ScheduledTasks;
import com.wimdetroyer.actuator.endpoints.sessions.Sessions;
import com.wimdetroyer.actuator.endpoints.shutdown.Shutdown;
import com.wimdetroyer.actuator.endpoints.startup.Startup;
import com.wimdetroyer.actuator.endpoints.threaddump.ThreadDump;
import org.springframework.web.client.RestClient;

/**
 * Fluent API client for interacting with Spring Boot Actuator endpoints.
 */
public class ActuatorClient {

    private final RestClient restClient;
    private final String actuatorBasePath;

    public ActuatorClient(RestClient restClient, String actuatorBasePath) {
        this.restClient = restClient;
        this.actuatorBasePath = actuatorBasePath;
    }

    /**
     * Access the health endpoint operations.
     */
    public Health health() {
        return new Health(restClient, actuatorBasePath);
    }

    /**
     * Access the info endpoint operations.
     */
    public Info info() {
        return new Info(restClient, actuatorBasePath);
    }

    /**
     * Access the beans endpoint operations.
     */
    public Beans beans() {
        return new Beans(restClient, actuatorBasePath);
    }

    /**
     * Access the caches endpoint operations.
     */
    public Caches caches() {
        return new Caches(restClient, actuatorBasePath);
    }

    /**
     * Access the conditions endpoint operations.
     */
    public Conditions conditions() {
        return new Conditions(restClient, actuatorBasePath);
    }

    /**
     * Access the configprops endpoint operations.
     */
    public ConfigProps configProps() {
        return new ConfigProps(restClient, actuatorBasePath);
    }

    /**
     * Access the env endpoint operations.
     */
    public Env env() {
        return new Env(restClient, actuatorBasePath);
    }

    /**
     * Access the flyway endpoint operations.
     */
    public Flyway flyway() {
        return new Flyway(restClient, actuatorBasePath);
    }

    /**
     * Access the heapdump endpoint operations.
     */
    public HeapDump heapDump() {
        return new HeapDump(restClient, actuatorBasePath);
    }

    /**
     * Access the httpexchanges endpoint operations.
     */
    public HttpExchanges httpExchanges() {
        return new HttpExchanges(restClient, actuatorBasePath);
    }

    /**
     * Access the integrationgraph endpoint operations.
     */
    public IntegrationGraph integrationGraph() {
        return new IntegrationGraph(restClient, actuatorBasePath);
    }

    /**
     * Access the liquibase endpoint operations.
     */
    public Liquibase liquibase() {
        return new Liquibase(restClient, actuatorBasePath);
    }

    /**
     * Access the logfile endpoint operations.
     */
    public LogFile logFile() {
        return new LogFile(restClient, actuatorBasePath);
    }

    /**
     * Access the loggers endpoint operations.
     */
    public Loggers loggers() {
        return new Loggers(restClient, actuatorBasePath);
    }

    /**
     * Access the mappings endpoint operations.
     */
    public Mappings mappings() {
        return new Mappings(restClient, actuatorBasePath);
    }

    /**
     * Access the metrics endpoint operations.
     */
    public Metrics metrics() {
        return new Metrics(restClient, actuatorBasePath);
    }

    /**
     * Access the prometheus endpoint operations.
     */
    public Prometheus prometheus() {
        return new Prometheus(restClient, actuatorBasePath);
    }

    /**
     * Access the quartz endpoint operations.
     */
    public Quartz quartz() {
        return new Quartz(restClient, actuatorBasePath);
    }

    /**
     * Access the sbom endpoint operations.
     */
    public Sbom sbom() {
        return new Sbom(restClient, actuatorBasePath);
    }

    /**
     * Access the scheduledtasks endpoint operations.
     */
    public ScheduledTasks scheduledTasks() {
        return new ScheduledTasks(restClient, actuatorBasePath);
    }

    /**
     * Access the sessions endpoint operations.
     */
    public Sessions sessions() {
        return new Sessions(restClient, actuatorBasePath);
    }

    /**
     * Access the shutdown endpoint operations.
     */
    public Shutdown shutdown() {
        return new Shutdown(restClient, actuatorBasePath);
    }

    /**
     * Access the startup endpoint operations.
     */
    public Startup startup() {
        return new Startup(restClient, actuatorBasePath);
    }

    /**
     * Access the threaddump endpoint operations.
     */
    public ThreadDump threadDump() {
        return new ThreadDump(restClient, actuatorBasePath);
    }
}

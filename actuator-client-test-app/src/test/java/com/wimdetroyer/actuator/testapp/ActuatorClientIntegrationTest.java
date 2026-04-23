package com.wimdetroyer.actuator.testapp;

import com.wimdetroyer.actuator.client.ActuatorClient;
import com.wimdetroyer.actuator.client.ActuatorClientFactory;
import com.wimdetroyer.actuator.endpoints.beans.BeansResponse;
import com.wimdetroyer.actuator.endpoints.caches.CacheResponse;
import com.wimdetroyer.actuator.endpoints.caches.CachesResponse;
import com.wimdetroyer.actuator.endpoints.conditions.ConditionsResponse;
import com.wimdetroyer.actuator.endpoints.configprops.ConfigPropsResponse;
import com.wimdetroyer.actuator.endpoints.env.EnvPropertyResponse;
import com.wimdetroyer.actuator.endpoints.env.EnvResponse;
import com.wimdetroyer.actuator.endpoints.flyway.FlywayResponse;
import com.wimdetroyer.actuator.endpoints.health.HealthResponse;
import com.wimdetroyer.actuator.endpoints.health.HealthStatus;
import com.wimdetroyer.actuator.endpoints.health.details.DataSourceDetails;
import com.wimdetroyer.actuator.endpoints.health.details.DiskSpaceDetails;
import com.wimdetroyer.actuator.endpoints.httpexchanges.HttpExchangesResponse;
import com.wimdetroyer.actuator.endpoints.info.InfoResponse;
import com.wimdetroyer.actuator.endpoints.integrationgraph.IntegrationGraphResponse;
import com.wimdetroyer.actuator.endpoints.loggers.LogLevel;
import com.wimdetroyer.actuator.endpoints.loggers.LoggerResponse;
import com.wimdetroyer.actuator.endpoints.loggers.LoggersResponse;
import com.wimdetroyer.actuator.endpoints.mappings.MappingsResponse;
import com.wimdetroyer.actuator.endpoints.metrics.MetricResponse;
import com.wimdetroyer.actuator.endpoints.metrics.MetricsResponse;
import com.wimdetroyer.actuator.endpoints.quartz.QuartzJobDetailResponse;
import com.wimdetroyer.actuator.endpoints.quartz.QuartzJobGroupResponse;
import com.wimdetroyer.actuator.endpoints.quartz.QuartzJobsResponse;
import com.wimdetroyer.actuator.endpoints.quartz.QuartzResponse;
import com.wimdetroyer.actuator.endpoints.quartz.QuartzTriggersResponse;
import com.wimdetroyer.actuator.endpoints.scheduledtasks.ScheduledTasksResponse;
import com.wimdetroyer.actuator.endpoints.threaddump.ThreadDumpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorClientIntegrationTest {

    @LocalServerPort
    private int port;

    private ActuatorClient client;

    @BeforeEach
    void setUp() {
        ActuatorClientFactory factory = new ActuatorClientFactory(Duration.ofSeconds(5), Duration.ofSeconds(10));
        client = factory.forUrl("http://localhost:" + port);
    }

    @Test
    void health_shouldReturnHealthStatus() {
        HealthResponse health = client.health().get();

        assertThat(health).isNotNull();
        assertThat(health.status()).isEqualTo(HealthStatus.UP);
    }

    @Test
    void health_shouldReturnComponentHealth() {
        HealthResponse health = client.health().getComponent("db");

        assertThat(health).isNotNull();
        assertThat(health.status()).isIn(HealthStatus.UP, HealthStatus.DOWN);
    }

    @Test
    void info_shouldReturnAppInfo() {
        InfoResponse info = client.info().get();

        assertThat(info).isNotNull();
    }

    @Test
    void beans_shouldReturnBeans() {
        BeansResponse beans = client.beans().get();

        assertThat(beans).isNotNull();
        assertThat(beans.contexts()).isNotEmpty();
    }

    @Test
    void caches_shouldReturnCaches() {
        CachesResponse caches = client.caches().getAll();

        assertThat(caches).isNotNull();
        assertThat(caches.cacheManagers()).isNotEmpty();
    }

    @Test
    void caches_shouldReturnSpecificCache() {
        CacheResponse cache = client.caches().get("users");

        assertThat(cache).isNotNull();
        assertThat(cache.name()).isEqualTo("users");
    }

    @Test
    void conditions_shouldReturnConditions() {
        ConditionsResponse conditions = client.conditions().get();

        assertThat(conditions).isNotNull();
        assertThat(conditions.contexts()).isNotEmpty();
    }

    @Test
    void configProps_shouldReturnConfigProps() {
        ConfigPropsResponse configProps = client.configProps().getAll();

        assertThat(configProps).isNotNull();
        assertThat(configProps.contexts()).isNotEmpty();
    }

    @Test
    void env_shouldReturnEnvironment() {
        EnvResponse env = client.env().getAll();

        assertThat(env).isNotNull();
        assertThat(env.propertySources()).isNotEmpty();
    }

    @Test
    void env_shouldReturnSpecificProperty() {
        EnvPropertyResponse property = client.env().get("server.port");

        assertThat(property).isNotNull();
    }

    @Test
    void flyway_shouldReturnFlywayInfo() {
        FlywayResponse flyway = client.flyway().get();

        assertThat(flyway).isNotNull();
        assertThat(flyway.contexts()).isNotEmpty();
    }

    @Test
    void loggers_shouldReturnLoggers() {
        LoggersResponse loggers = client.loggers().getAll();

        assertThat(loggers).isNotNull();
        assertThat(loggers.loggers()).isNotEmpty();
        assertThat(loggers.levels()).contains(LogLevel.DEBUG, LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR);
    }

    @Test
    void loggers_shouldReturnSpecificLogger() {
        LoggerResponse logger = client.loggers().get("com.wimdetroyer");

        assertThat(logger).isNotNull();
        assertThat(logger.effectiveLevel()).isNotNull();
    }

    @Test
    void loggers_shouldSetLoggerLevel() {
        // Set level
        client.loggers().setLevel("com.wimdetroyer.actuator.testapp")
                .level(LogLevel.TRACE)
                .execute();

        // Verify
        LoggerResponse logger = client.loggers().get("com.wimdetroyer.actuator.testapp");
        assertThat(logger.configuredLevel()).isEqualTo(LogLevel.TRACE);

        // Reset
        client.loggers().setLevel("com.wimdetroyer.actuator.testapp")
                .clear();
        assertThat(client.loggers().get("com.wimdetroyer.actuator.testapp").configuredLevel()).isNull();
    }

    @Test
    void mappings_shouldReturnMappings() {
        MappingsResponse mappings = client.mappings().get();

        assertThat(mappings).isNotNull();
        assertThat(mappings.contexts()).isNotEmpty();
    }

    @Test
    void metrics_shouldReturnMetricNames() {
        MetricsResponse metrics = client.metrics().getAll();

        assertThat(metrics).isNotNull();
        assertThat(metrics.names()).isNotEmpty();
        assertThat(metrics.names()).contains("jvm.memory.used");
    }

    @Test
    void metrics_shouldReturnSpecificMetric() {
        MetricResponse metric = client.metrics().get("jvm.memory.used").execute();

        assertThat(metric).isNotNull();
        assertThat(metric.name()).isEqualTo("jvm.memory.used");
        assertThat(metric.measurements()).isNotEmpty();
    }

    @Test
    void metrics_shouldReturnMetricWithTags() {
        MetricResponse metric = client.metrics().get("jvm.memory.used")
                .tag("area", "heap")
                .execute();

        assertThat(metric).isNotNull();
        assertThat(metric.name()).isEqualTo("jvm.memory.used");
    }

    @Test
    void quartz_shouldReturnQuartzOverview() {
        QuartzResponse quartz = client.quartz().get();

        assertThat(quartz).isNotNull();
        assertThat(quartz.jobs()).isNotNull();
        assertThat(quartz.jobs().groups()).isNotEmpty();
        assertThat(quartz.triggers()).isNotNull();
        assertThat(quartz.triggers().groups()).isNotEmpty();
    }

    @Test
    void quartz_shouldReturnJobs() {
        QuartzJobsResponse jobs = client.quartz().getJobs();

        assertThat(jobs).isNotNull();
        assertThat(jobs.groups()).isNotEmpty();
    }

    @Test
    void quartz_shouldReturnTriggers() {
        QuartzTriggersResponse triggers = client.quartz().getTriggers();

        assertThat(triggers).isNotNull();
        assertThat(triggers.groups()).isNotEmpty();
    }

    @Test
    void quartz_shouldReturnJobGroup() {
        QuartzJobGroupResponse jobGroup = client.quartz().getJobGroup("sampleGroup");

        assertThat(jobGroup).isNotNull();
        assertThat(jobGroup.group()).isEqualTo("sampleGroup");
    }

    @Test
    void quartz_shouldReturnJobDetail() {
        QuartzJobDetailResponse job = client.quartz().getJob("sampleGroup", "sampleJob");

        assertThat(job).isNotNull();
        assertThat(job.group()).isEqualTo("sampleGroup");
        assertThat(job.name()).isEqualTo("sampleJob");
    }

    @Test
    void scheduledTasks_shouldReturnScheduledTasks() {
        ScheduledTasksResponse tasks = client.scheduledTasks().get();

        assertThat(tasks).isNotNull();
        // We have cron, fixedRate, and fixedDelay tasks defined
        assertThat(tasks.cron()).isNotEmpty();
        assertThat(tasks.fixedRate()).isNotEmpty();
        assertThat(tasks.fixedDelay()).isNotEmpty();
    }

    @Test
    void threadDump_shouldReturnThreadDumpAsJson() {
        ThreadDumpResponse dump = client.threadDump().get();

        assertThat(dump).isNotNull();
        assertThat(dump.threads()).isNotEmpty();
        assertThat(dump.threads().getFirst().blockedTime()).isLessThanOrEqualTo(Long.MAX_VALUE);
    }

    @Test
    void httpExchanges_shouldReturnExchangesWithDuration() {
        client.health().get();

        HttpExchangesResponse exchanges = client.httpExchanges().get();

        assertThat(exchanges).isNotNull();
        assertThat(exchanges.exchanges()).anySatisfy(exchange -> assertThat(exchange.timeTaken()).isNotNull());
    }

    @Test
    void threadDump_shouldReturnThreadDumpAsText() {
        String dump = client.threadDump().getAsText();

        assertThat(dump).isNotNull();
        assertThat(dump).contains("Thread");
    }

    @Test
    void integrationGraph_shouldReturnGraph() {
        IntegrationGraphResponse graph = client.integrationGraph().get();

        assertThat(graph).isNotNull();
        assertThat(graph.nodes()).isNotEmpty();
    }

    // =============================================
    // Typed Accessor Tests
    // =============================================

    @Test
    void health_shouldParseDiskSpaceDetails() {
        HealthResponse health = client.health().get();

        HealthResponse.HealthComponent diskSpace = health.components().get("diskSpace");
        assertThat(diskSpace).isNotNull();
        assertThat(diskSpace.details()).isInstanceOf(DiskSpaceDetails.class);
        if (diskSpace.details() instanceof DiskSpaceDetails ds) {
            assertThat(ds.total()).isPositive();
            assertThat(ds.free()).isPositive();
            assertThat(ds.threshold()).isPositive();
            assertThat(ds.exists()).isTrue();
        }
    }

    @Test
    void health_shouldParseDataSourceDetails() {
        HealthResponse health = client.health().get();

        HealthResponse.HealthComponent db = health.components().get("db");
        assertThat(db).isNotNull();
        assertThat(db.details()).isInstanceOf(DataSourceDetails.class);
        if (db.details() instanceof DataSourceDetails ds) {
            assertThat(ds.database()).isEqualTo("H2");
        }
    }

    @Test
    void info_shouldReturnTypedJavaInfo() {
        InfoResponse info = client.info().get();

        assertThat(info.java()).isNotNull();
        assertThat(info.java().version()).isNotBlank();
        assertThat(info.java().vendor()).isNotNull();
    }

    @Test
    void info_shouldReturnTypedOsInfo() {
        InfoResponse info = client.info().get();

        assertThat(info.os()).isNotNull();
        assertThat(info.os().name()).isNotBlank();
        assertThat(info.os().arch()).isNotBlank();
    }

    @Test
    void env_shouldReturnTypedPropertyValue() {
        EnvPropertyResponse property = client.env().get("server.port");

        assertThat(property.property().typed().asInteger()).isPresent();
    }

    @Test
    void env_shouldDetectSanitizedValues() {
        EnvResponse env = client.env().getAll();

        assertThat(env.propertySources()).isNotEmpty();
        env.propertySources().stream()
                .filter(ps -> ps.properties() != null && !ps.properties().isEmpty())
                .findFirst()
                .ifPresent(ps -> {
                    ps.properties().values().stream()
                            .findFirst()
                            .ifPresent(pv -> assertThat(pv.typed()).isNotNull());
                });
    }

    @Test
    void configProps_shouldNavigateNestedProperties() {
        ConfigPropsResponse configProps = client.configProps().getAll();

        configProps.contexts().values().stream()
                .flatMap(ctx -> ctx.beans().values().stream())
                .filter(bean -> bean.properties() != null && !bean.properties().isEmpty())
                .findFirst()
                .ifPresent(bean -> {
                    assertThat(bean.navigator()).isNotNull();
                    bean.properties().keySet().stream()
                            .findFirst()
                            .ifPresent(key -> assertThat(bean.navigator().get(key)).isPresent());
                });
    }

    @Test
    void quartz_shouldReturnJobDataAccessor() {
        QuartzJobDetailResponse job = client.quartz().getJob("sampleGroup", "sampleJob");

        assertThat(job.dataAccessor()).isNotNull();
        assertThat(job.dataAccessor().isEmpty()).isTrue();
    }
}

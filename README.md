# Actuator Client Spring Boot Starter

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java 25](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/projects/jdk/25/)

A Spring Boot starter providing a fluent, type-safe Java client for [Spring Boot Actuator](https://docs.spring.io/spring-boot/reference/actuator/) REST endpoints. The response DTOs were reverse-engineered from the official [Spring Boot Actuator REST API documentation](https://docs.spring.io/spring-boot/api/rest/actuator/), allowing you to programmatically interact with any Spring Boot application's actuator endpoints from Java code.

## Requirements

- Java 25 or newer
- Spring Boot 4.0 or newer

## Features

- **24 actuator endpoint clients** covering health, info, metrics, loggers, env, beans, caches, conditions, configprops, flyway, heapdump, httpexchanges, integrationgraph, liquibase, logfile, mappings, prometheus, quartz, sbom, scheduledtasks, sessions, shutdown, startup, and threaddump
- **Fluent API** with builder patterns for complex requests (metrics with tag filtering, prometheus format selection, log file range requests, etc.)
- **Type-safe response DTOs** using Java records for all endpoint responses
- **Typed health details** with Jackson polymorphic deserialization (DiskSpace, DataSource, with extensible UnknownHealthDetails fallback)
- **Typed info sections** (Java, OS, Build, Git, Process)
- **Environment property type coercion** via `TypedPropertyValue`
- **Config property navigation** via `ConfigPropertyNavigator` (dot-separated path navigation through nested maps)
- **Quartz job data accessor** via `JobDataAccessor` (type-safe access to Quartz job data maps)
- **Spring Boot auto-configuration** with customizable timeouts
- **`ActuatorClientFactory`** for dynamic multi-target URL construction (monitor multiple services from one client)
- **No embedded Tomcat** pulled into consumers -- uses `spring-web` RestClient directly

## Getting Started

### Installation

```xml
<dependency>
    <groupId>com.wimdetroyer</groupId>
    <artifactId>actuator-client-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Configuration

```yaml
actuator:
  client:
    base-url: http://localhost:8080    # target application URL
    actuator-path: /actuator           # optional, default: /actuator
    connect-timeout: 5s                # optional, default: 5s
    read-timeout: 10s                  # optional, default: 10s
```

### Auto-Configured Beans

| Bean | Condition | Description |
|------|-----------|-------------|
| `ActuatorClientFactory` | Always | Factory for creating `ActuatorClient` instances for any target URL |
| `ActuatorClient` | When `actuator.client.base-url` is set | Pre-configured client for a single target |

## Usage Examples

### Health Monitoring

```java
@Autowired ActuatorClient actuatorClient;

// Get overall health
HealthResponse health = actuatorClient.health().get();
if (health.status() == HealthStatus.UP) {
    // Application is healthy
}

// Get health for a specific component
HealthResponse dbHealth = actuatorClient.health().getComponent("db");

// Access typed health details
health.components().forEach((name, component) -> {
    if (component.details() instanceof DiskSpaceDetails disk) {
        System.out.println("Free disk: " + disk.free());
    }
});
```

### Version Detection via /info

```java
InfoResponse info = actuatorClient.info().get();
String version = info.build().version();
String javaVersion = info.java().version();
```

### Graceful Shutdown

```java
ShutdownResponse response = actuatorClient.shutdown().execute();
System.out.println(response.message()); // "Shutting down, bye!"
```

### Metrics Collection with Tag Filtering

```java
// Get JVM memory usage filtered by heap area
MetricResponse memory = actuatorClient.metrics().get("jvm.memory.used")
    .tag("area", "heap")
    .execute();

double usedBytes = memory.measurements().getFirst().value();

// Get CPU usage
MetricResponse cpu = actuatorClient.metrics().get("process.cpu.usage").execute();
```

### Dynamic Multi-Target Usage

```java
@Autowired ActuatorClientFactory factory;

// Create clients for different services dynamically
ActuatorClient serviceA = factory.forUrl("http://service-a:8080");
ActuatorClient serviceB = factory.forUrl("http://service-b:8080");

HealthResponse healthA = serviceA.health().get();
HealthResponse healthB = serviceB.health().get();
```

### Logger Level Management

```java
// Set log level at runtime
actuatorClient.loggers().setLevel("com.myapp")
    .level(LogLevel.DEBUG)
    .execute();

// Clear configured level (reset to effective level from parent)
actuatorClient.loggers().setLevel("com.myapp").clear();

// Get all loggers
LoggersResponse loggers = actuatorClient.loggers().getAll();
```

### Environment Properties

```java
EnvResponse env = actuatorClient.env().getAll();
System.out.println("Active profiles: " + env.activeProfiles());

// Get a specific property with type coercion
EnvPropertyResponse prop = actuatorClient.env().get("server.port");
Integer port = prop.propertySources().stream()
    .flatMap(ps -> ps.properties().entrySet().stream())
    .findFirst()
    .map(e -> new TypedPropertyValue(e.getValue().value()).asInteger())
    .orElse(null);
```

### Configuration Properties Navigation

```java
ConfigPropsResponse configProps = actuatorClient.configProps().get();
// Navigate nested properties with dot notation
ConfigPropertyNavigator nav = new ConfigPropertyNavigator(configProps);
String value = nav.getString("spring.datasource.url");
```

## Available Endpoints

| Method | Description |
|--------|-------------|
| `health()` | Health checks (overall, by component, by sub-component) |
| `info()` | Application info (Java, OS, build, git, process) |
| `metrics()` | Metrics (list names, get metric with tag filtering) |
| `loggers()` | Logger management (get all, get/set individual levels) |
| `env()` | Environment properties (all, by property name) |
| `beans()` | Spring bean definitions |
| `conditions()` | Auto-configuration conditions report |
| `configProps()` | Configuration properties |
| `caches()` | Cache management (list, get, clear) |
| `mappings()` | Request mappings |
| `flyway()` | Flyway migration info |
| `liquibase()` | Liquibase migration info |
| `scheduledTasks()` | Scheduled tasks |
| `quartz()` | Quartz scheduler (jobs, triggers, groups) |
| `threadDump()` | Thread dump (JSON or text) |
| `heapDump()` | Heap dump download |
| `httpExchanges()` | HTTP exchange history |
| `integrationGraph()` | Spring Integration graph |
| `logFile()` | Log file retrieval (with range support) |
| `startup()` | Application startup events |
| `shutdown()` | Graceful shutdown |
| `sbom()` | Software Bill of Materials |
| `sessions()` | HTTP sessions |
| `prometheus()` | Prometheus-format metrics |

## Project Structure

This is a multi-module Maven project:

- **`actuator-client/`** -- The starter library itself. This is the artifact published to Maven Central (`actuator-client-spring-boot-starter`).
- **`actuator-client-test-app/`** -- A Spring Boot test application used for integration testing. It wires up all supported actuator endpoints (H2, JPA, Flyway, Liquibase, Quartz, Caffeine caches, Spring Integration, sessions, HTTP exchanges, etc.) so the client can be tested against real actuator responses. **Not included in the release** -- only used during development and CI.

To run the integration tests locally:

```bash
mvn clean test
```

## Disclaimer

I don't provide any guarantees about how well this library functions. The response DTOs were reverse-engineered from the Spring Boot Actuator REST API documentation, not from any official SDK or client library. I only use a subset of the endpoints in a hobby project. Moreover, this library was largely created with the help of Claude Code.
It does work well though in my experience, and I'm sure it'll save you some time to use this.

## License

[Apache License 2.0](LICENSE)

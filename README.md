# micrometer-poc

## Description

Project demonstrates how to integrate SpringBoot Actuator, Micrometer, Prometheus and Grafana
and implement basic meters:
 - counter - value that increments
 - timer - duration of some action
 - gauge - current value of meter

## How to start

Run docker compose:

```docker compose up```

Run application:

```./gradlew clean bootRun```

Observe Grafana dashboard 'JVM Micrometer' at http://localhost:3000/

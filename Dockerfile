FROM openjdk:11

COPY target/spring-boot-telemetry-1.0-SNAPSHOT.jar app.jar
RUN mkdir /otel
COPY lib/opentelemetry-javaagent-all.jar /otel/opentelemetry-javaagent-all.jar

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/urandom", "-javaagent:/otel/opentelemetry-javaagent-all.jar", "-Dotel.exporter=otlp_span", "-Dotel.exporter.otlp.endpoint=grafana-agent-traces.fo-monitoring.svc.cluster.local:55780", "-Dotel.exporter.otlp.insecure=true", "-jar", "app.jar"]

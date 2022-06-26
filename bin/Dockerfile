FROM openjdk:8-jdk-alpine

RUN mkdir -p /app

COPY target/FORTICAS/gestion-restau-backend.jar app/gestion-restau-backend.jar
COPY target/FORTICAS/lib app/lib
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/gestion-restau-backend.jar"]


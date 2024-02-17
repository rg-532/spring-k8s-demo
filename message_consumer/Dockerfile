# syntax=docker/dockerfile:1
FROM eclipse-temurin:17.0.10_7-jdk

COPY ./build/libs/message_consumer-1.0.jar app.jar
EXPOSE 8082

CMD ["java", "-jar", "app.jar"]
FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./target/gateway-service-1.0-SNAPSHOT.jar ./gateway-service.jar
EXPOSE 8080
CMD ["java", "-jar", "gateway-service.jar"]
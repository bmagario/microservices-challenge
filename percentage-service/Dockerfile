FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./target/percentage-service-1.0-SNAPSHOT.jar ./percentage-service.jar
CMD ["java", "-jar", "percentage-service.jar"]
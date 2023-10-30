FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./target/sum-calculator-service-1.0-SNAPSHOT.jar ./sum-calculator-service.jar
CMD ["java", "-jar", "sum-calculator-service.jar"]
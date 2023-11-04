FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./gateway-service/target/gateway-service-1.0-SNAPSHOT.jar ./gateway-service.jar
COPY ./wait /app/
EXPOSE 8080
# CMD ["java", "-jar", "gateway-service.jar"]
CMD ./wait && java -jar gateway-service.jar
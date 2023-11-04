FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./percentage-service/target/percentage-service-1.0-SNAPSHOT.jar ./percentage-service.jar
COPY ./wait /app/
#CMD ["java", "-jar", "percentage-service.jar"]
CMD ./wait && java -jar percentage-service.jar
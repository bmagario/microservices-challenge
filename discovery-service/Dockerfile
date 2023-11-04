FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./discovery-service/target/discovery-service-1.0-SNAPSHOT.jar ./discovery-service.jar
EXPOSE 8761
CMD ["java", "-jar", "discovery-service.jar"]

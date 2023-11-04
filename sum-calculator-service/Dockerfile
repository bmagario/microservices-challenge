FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./sum-calculator-service/target/sum-calculator-service-1.0-SNAPSHOT.jar ./sum-calculator-service.jar
COPY ./wait /app/
#CMD ["java", "-jar", "sum-calculator-service.jar"]
CMD ./wait && java -jar sum-calculator-service.jar
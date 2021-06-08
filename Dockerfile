FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/CurrencyConversionFactor-Service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
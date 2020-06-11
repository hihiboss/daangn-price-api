FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} daangn-price-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/daangn-price-api.jar"]
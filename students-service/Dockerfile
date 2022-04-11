FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*-exec.jar
COPY ${JAR_FILE} main-server.jar
ENTRYPOINT ["java", "-jar", "/main-server.jar"]
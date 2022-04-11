FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/files-server.jar
COPY ${JAR_FILE} files-server.jar
WORKDIR /
RUN mkdir /files
RUN chmod ugo+w /files
ENTRYPOINT ["java", "-jar", "/files-server.jar"]
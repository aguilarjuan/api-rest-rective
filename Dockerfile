FROM adoptopenjdk/openjdk11:latest
RUN mkdir /usr/src/zaraDocker
COPY ./target/zara-0.0.1-SNAPSHOT.jar /usr/src/zaraDocker
WORKDIR /usr/src/zaraDocker
EXPOSE 8080
CMD ["java", "-jar", "/usr/src/zaraDocker/zara-0.0.1-SNAPSHOT.jar"]

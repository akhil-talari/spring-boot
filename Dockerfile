FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/secondproject-1.0.0.jar api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]
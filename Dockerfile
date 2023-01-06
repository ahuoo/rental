FROM openjdk:8
COPY target/rental-api-service-0.0.1-SNAPSHOT.jar Spring-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Spring-docker.jar"]
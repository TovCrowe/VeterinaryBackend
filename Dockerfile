FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/vet-0.0.1-SNAPSHOT.jar vet.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "vet.jar" ]
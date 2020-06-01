FROM maven:3.5.3-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Hugo Blanc
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn -Dmaven.test.skip  package


FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/synergieFamily-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "synergieFamily-0.0.1-SNAPSHOT.jar"]
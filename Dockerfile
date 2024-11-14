FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean verify

FROM maven:3.9.8-eclipse-temurin-21-alpine

COPY --from=builder target/*jar dslist.jar
EXPOSE 8080

CMD ["java", "-jar", "dslist.jar"]
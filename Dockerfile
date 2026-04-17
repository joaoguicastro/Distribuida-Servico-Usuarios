FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN apk add --no-cache wget
RUN addgroup -S healthsys && adduser -S healthsys -G healthsys
USER healthsys

COPY --from=build /app/target/servico-usuarios-*.jar app.jar

EXPOSE 8084

ENV SPRING_PROFILES_ACTIVE=docker
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_NAME=usuarios_db
ENV DB_USER=healthsys
ENV DB_PASS=healthsys
ENV JWT_SECRET=healthsys-jwt-secret-key-deve-ser-trocada-em-producao
ENV JWT_EXPIRATION=86400000

ENTRYPOINT ["java", "-jar", "app.jar"]

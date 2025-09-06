# ---- build stage ----
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---- runtime stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app
# copy the fat jar produced by Spring Boot
COPY --from=build /app/target/*.jar /app/app.jar
# Render sets $PORT; bind Spring to it
ENV PORT=8080
EXPOSE 8080
CMD ["sh","-c","java -Dserver.port=${PORT} -jar /app/app.jar"]

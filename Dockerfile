FROM amazoncorretto:17-alpine-jdk AS builder

WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM amazoncorretto:17
EXPOSE 8080

CMD ["java", "-jar", "./build/libs/itec-0.0.1-SNAPSHOT.jar"]

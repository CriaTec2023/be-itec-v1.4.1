FROM amazoncorretto:17-alpine-jdk AS builder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . .
RUN ./gradlew clean build -x test

FROM amazoncorretto:17
EXPOSE 8080
WORKDIR /app
COPY --from=builder /usr/src/app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
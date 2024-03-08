FROM amazoncorretto:17-alpine-jdk AS builder

WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test


FROM amazoncorretto:17
EXPOSE 8080

COPY --from=builder /app/build/libs/itec-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "itec-0.0.1-SNAPSHOT.jar"]
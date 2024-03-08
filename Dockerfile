FROM amazoncorretto:17-alpine-jdk AS builder

WORKDIR /app

COPY . .

# Adicione as instruções aqui
RUN apk update && \
    apk add --no-cache curl && \
    curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apk add --no-cache nodejs && \
    npm install -g npm

RUN ./gradlew clean build -x test

FROM amazoncorretto:17

EXPOSE 8080

COPY --from=builder /app/build/libs/itec-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "itec-0.0.1-SNAPSHOT.jar"]
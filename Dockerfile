FROM amazoncorretto:17
EXPOSE 8080

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

RUN gradle --version

COPY --from=build ~/.gradle /root/.gradle

COPY . .
RUN gradle clean build -x test

COPY --from=build /usr/src/app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
FROM gradle:8.5.0-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM amazoncorretto:17
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/itec-0.0.3-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/itec-0.0.3-SNAPSHOT.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/itec-0.0.3-SNAPSHOT.jar"]

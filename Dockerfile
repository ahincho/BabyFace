FROM amazoncorretto:21-alpine AS builder
WORKDIR /app
COPY target/baby-face-1.0.0.jar /app/app.jar
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /app/app.jar /app/app.jar
EXPOSE 8080
ENV SPRING_PROFILE=dev
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=${SPRING_PROFILE}"]

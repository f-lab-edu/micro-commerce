FROM openjdk:17-jdk-slim

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}","-jar", "app.jar"]

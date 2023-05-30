# Use a base image with Java support
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/word-counter-microservice-1.0-SNAPSHOT /app/WordCounter.jar

# Expose the port your application will be listening on
EXPOSE 8080

# Set the entrypoint command to run your application
ENTRYPOINT ["java", "-jar", "/app/WordCounter.jar"]

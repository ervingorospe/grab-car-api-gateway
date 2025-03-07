# Use official OpenJDK base image
FROM eclipse-temurin:23-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 9090

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
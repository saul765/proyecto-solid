# Use the official maven/Java 17 image to create a build artifact.
FROM maven:3.8.1-openjdk-17-slim AS build

# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file to our app directory
COPY pom.xml .

# This command downloads the project dependencies and stores them in a local repository.
# It's a separate step so the dependencies will be cached unless changes to pom.xml are made.
RUN mvn dependency:go-offline -B

# Copy the rest of the code
COPY src /app/src

# Build the project
RUN mvn package

# Use the official openjdk image for a lean production stage of our multi-stage build
FROM openjdk:17-slim

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/proyecto-solid-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
# Run the application
CMD ["java", "-jar", "app.jar"]
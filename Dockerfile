FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY ./build/libs/ingredientservice-0.0.1-SNAPSHOT.jar ./ingredientservice-0.0.1-SNAPSHOT.jar

# Run the application
ENTRYPOINT ["java", "-jar", "ingredientservice-0.0.1-SNAPSHOT.jar"]

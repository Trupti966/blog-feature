# Use an OpenJDK base image
FROM openjdk:latest

# Set the working directory in the container
WORKDIR /usr/app

# Copy the packaged JAR file into the container
COPY target/post_feature_docker_app.jar /usr/app/


# Expose the port your app runs on (change 8081 if your app runs on a different port)
EXPOSE 8081

# Define the command to run your application
CMD ["java", "-jar", "post_feature_docker_app.jar"]

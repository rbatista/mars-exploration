FROM openjdk:8-jre-alpine

# Create the app directory
RUN mkdir -p /app
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Command to execute the application
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "mars-exploration.jar"]

# Add application executable jar
ARG JAR_FILE=target/mars-exploration-*.jar
ADD $JAR_FILE mars-exploration.jar

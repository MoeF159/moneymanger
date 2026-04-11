# -------------------------------
# Stage 1: Build the application
# -------------------------------
# Use the full JDK to compile the project
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy over the Maven wrapper and config first
# Doing this separately helps Docker cache dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permission so the Maven wrapper can execute on Deployment
RUN chmod +x mvnw

# Download dependencies ahead of time
RUN ./mvnw dependency:go-offline -B

# Now bring in the actual source code
COPY src src

# Build the jar file
# Skipping tests here to keep deploys faster
RUN ./mvnw clean package -DskipTests


# -------------------------------
# Stage 2: Run the application
# -------------------------------
# Switch to a lighter image since we only need to run the app now
FROM eclipse-temurin:21-jre

# Same working directory
WORKDIR /app

# Grab the jar built in the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Default Spring port (Render will override this with PORT anyway)
EXPOSE 8080

# Start the app
# No need to mess with ports here — Spring handles it using server.port
ENTRYPOINT ["java", "-jar", "app.jar"]
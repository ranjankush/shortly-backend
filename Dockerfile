# Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy only the final jar from the build stage
COPY --from=build /app/target/url-shortner-0.0.1-SNAPSHOT.jar app.jar

# Create a non-root user
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring

EXPOSE 8085

# Run the application
CMD ["java", "-jar", "app.jar"]

# Stage 1: Build with Maven + JDK
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy only pom.xml first (to leverage docker cache)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copy project source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run
ENTRYPOINT ["java","-jar","/app/app.jar"]

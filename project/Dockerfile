FROM maven:4.0.0-openjdk-8 as build

COPY src /app/src
COPY pom.xml /app/pom.xml
COPY entry-point.sh /app/entry-point.sh

# Set the working directory
WORKDIR /app

# Give execution rights on the entry-point script
RUN chmod +x /app/entry-point.sh

# Build the application without running tests to speed up the build
RUN mvn clean install -DskipTests

FROM openjdk:8-jre-slim

COPY --from=build /app/target/*.jar /app/app.jar
COPY --from=build /app/entry-point.sh /app/entry-point.sh

# Set the working directory
WORKDIR /app

# Give execution rights on the entry-point script
RUN chmod +x /app/entry-point.sh

ENV PORT=8000

# Set the entry-point script to be executed when the container starts
ENTRYPOINT ["/app/entry-point.sh"]

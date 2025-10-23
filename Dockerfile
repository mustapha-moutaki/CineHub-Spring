# Use an official Tomcat runtime as a parent image
FROM tomcat:9.0-jdk17-temurin

# Set the working directory to Tomcat's webapps directory
WORKDIR /usr/local/tomcat/webapps

# Remove default ROOT application
RUN rm -rf ROOT

# Copy the WAR file into the webapps directory
# The WAR file will be built by Maven and placed in the 'target' directory
# We assume the WAR file is named mustapha-moutaki-cinehub-spring.war
COPY target/mustapha-moutaki-cinehub-spring-1.0-SNAPSHOT.war mustapha-moutaki-cinehub-spring.war

# Copy application.properties directly to the WAR's WEB-INF/classes
# This ensures it's available inside the deployed WAR
COPY src/main/resources/application.properties mustapha-moutaki-cinehub-spring/WEB-INF/classes/application.properties

# Expose port 8080 (default Tomcat port)
EXPOSE 8080

# Command to run Tomcat
CMD ["catalina.sh", "run"]
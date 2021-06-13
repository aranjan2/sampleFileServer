#FROM adoptopenjdk/openjdk11:latest
FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c
WORKDIR /app
COPY fileServer/ /app/fileServer/
COPY fs-cli/ /app/fs-cli/
COPY pom.xml /app
RUN ls -R /app/fileServer
RUN ls -R /app/fs-cli
#RUN apk add maven
RUN cd /app
RUN mvn -e -B dependency:resolve
RUN mvn -e -B package
#RUN ls   -R /app | grep -l *.jar && sleep 30
VOLUME /tmp
#ARG JAR_FILE

#COPY ./fileServer/target/fileServer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
#CMD "java" "-jar" "/app/fileServer/fileServer-0.0.1-SNAPSHOT.jar"
ENTRYPOINT exec java $JAVA_OPTS -jar /app/fileServer/target/fileServer-0.0.1-SNAPSHOT.jar


EXPOSE 8080
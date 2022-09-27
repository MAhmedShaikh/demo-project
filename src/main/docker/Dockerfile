FROM openjdk:17-alpine
VOLUME /tmp
ADD demo-project-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

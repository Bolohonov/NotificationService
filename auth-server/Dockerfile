FROM bellsoft/liberica-openjdk-alpine:17
COPY target/*.jar auth-server.jar
ENTRYPOINT ["java","-jar","auth-server.jar"]
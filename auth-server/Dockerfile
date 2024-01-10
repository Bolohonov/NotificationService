FROM bellsoft/liberica-openjdk-alpine:19
COPY target/*.jar authorization-server.jar
ENTRYPOINT ["java","-jar","authorization-server.jar"]
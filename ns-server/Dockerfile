FROM bellsoft/liberica-openjdk-alpine:17
COPY target/*.jar ns-server.jar
ENTRYPOINT ["java","-jar","ns-server.jar"]
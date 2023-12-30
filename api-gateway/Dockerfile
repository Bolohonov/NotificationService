FROM bellsoft/liberica-openjdk-alpine:17
COPY target/*.jar api-gateway.jar
ENTRYPOINT ["java","-jar","api-gateway.jar"]
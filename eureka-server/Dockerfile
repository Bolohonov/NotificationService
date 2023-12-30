FROM bellsoft/liberica-openjdk-alpine:17
COPY target/*.jar eureka-server.jar
ENTRYPOINT ["java","-jar","eureka-server.jar"]
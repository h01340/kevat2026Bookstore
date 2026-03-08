FROM eclipse-temurin:17-jdk

WORKDIR /opt/app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
FROM amazoncorretto:17
MAINTAINER rovargas
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]
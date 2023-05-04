FROM amazoncorretto:8-alpine-jdk
MAINTAINER rovargas
COPY out/artifacts/demo_jar2/demo.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]
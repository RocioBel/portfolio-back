FROM amazoncorretto:11
MAINTAINER rovargas
COPY out/artifacts/demo_jar2/demo.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]
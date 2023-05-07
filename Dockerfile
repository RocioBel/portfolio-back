FROM corretto:17
MAINTAINER rovargas
COPY out/artifacts/demo_jar/demo.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]
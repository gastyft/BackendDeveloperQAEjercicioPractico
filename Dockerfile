FROM amazoncorretto:17-al2-jdk
MAINTAINER gastyft
COPY target/dev-0.0.1-SNAPSHOT.jar dev-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dev-0.0.1-SNAPSHOT.jar"]

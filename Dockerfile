FROM maven:onbuild
CMD	["mvn", "clean verify"]

WORKDIR /opt

ADD target/camel-rest-spike*.jar /opt/camel-rest-spike.jar

ENTRYPOINT [ "java", "-jar", "/opt/camel-rest-spike.jar"]

EXPOSE 8080
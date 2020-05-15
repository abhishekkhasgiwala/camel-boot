FROM maven:3.5.2-jdk-8-alpine

RUN mkdir /app

COPY . /project

RUN cd /project/camel-boot-rest

WORKDIR /project/camel-boot-rest
RUN mvn clean install
RUN cp /project/camel-boot-rest/target/camel-boot-rest-0.0.1-SNAPSHOT.jar /app

WORKDIR /project/rest-api-stub
RUN mvn clean install
RUN cp /project/rest-api-stub/target/rest-api-stub-0.0.1-SNAPSHOT.jar /app

RUN ls /app
WORKDIR /app
# starting stub in background
#RUN java -jar rest-api-stub-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "camel-boot-rest-0.0.1-SNAPSHOT.jar"]

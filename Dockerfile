FROM openjdk:8-jdk-alpine

ADD target/transaction-enquiry-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
EXPOSE 8089
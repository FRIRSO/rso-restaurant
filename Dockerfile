FROM openjdk:8-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/restaurants-api-1.0-SNAPSHOT.jar /app

EXPOSE 8085

CMD java -jar restaurants-api-1.0-SNAPSHOT.jar
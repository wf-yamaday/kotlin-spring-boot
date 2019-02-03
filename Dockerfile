FROM ubuntu:16.04

RUN apt-get update && apt-get -y install openjdk-8-jdk
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64


VOLUME /tmp

RUN mkdir /app
COPY . /app
WORKDIR /app

RUN ./gradlew build

ENTRYPOINT java -jar ./build/libs/demo-0.0.1-SNAPSHOT.jar
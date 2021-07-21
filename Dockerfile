#This is a Image created for Close Data API
FROM maven:3.8.1-jdk-8-slim
MAINTAINER Santhosh Kumar <santhoshkumar.sm@gmail.com>

WORKDIR /sample
COPY src /sample/src
COPY pom.xml /sample
COPY testng.xml /sample
COPY features /sample/features
COPY libs /sample/libs
CMD ["echo","Image created"]
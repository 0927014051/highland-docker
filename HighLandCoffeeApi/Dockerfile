FROM openjdk:8
VOLUME /tmp
ADD target/HighLandCoffeeApi.jar HighLandCoffeeApi.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","HighLandCoffeeApi.jar"]
FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/shoppingcart-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} shoppingcart.jar
ENTRYPOINT ["java","-jar","/shoppingcart.jar"]
FROM maven:3.5-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-alpine
COPY --from=build /usr/src/app/target/*.jar /usr/app/pokemonspeare.jar
EXPOSE 8080 5005 9010
ENTRYPOINT ["java","-jar","/usr/app/pokemonspeare.jar"]


#LOCAL
CMD java \
 -Xdebug \
 -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n \
 -jar \
 -Dspring.profiles.active=local \
 -Dcom.sun.management.jmxremote=true \
 -Dcom.sun.management.jmxremote.local.only=false \
 -Dcom.sun.management.jmxremote.authenticate=false \
 -Dcom.sun.management.jmxremote.ssl=false \
 -Djava.rmi.server.hostname=localhost \
 -Dcom.sun.management.jmxremote.port=9010 \
 -Dcom.sun.management.jmxremote.rmi.port=9010 \
 pokemonspeare.jar

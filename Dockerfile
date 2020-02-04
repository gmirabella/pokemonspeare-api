FROM openjdk:8-jre-alpine
WORKDIR /home

COPY /target/*.jar pokemonspeare-api.jar

ENV PORT 8080
EXPOSE 8080 5005 9010

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
 pokemonspeare-api.jar

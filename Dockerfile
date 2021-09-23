FROM adoptopenjdk/openjdk11
ADD build/libs/Room-Occupancy-Application-1.0-SNAPSHOT.jar .
EXPOSE 8081
CMD java -jar Room-Occupancy-Application-1.0-SNAPSHOT.jar

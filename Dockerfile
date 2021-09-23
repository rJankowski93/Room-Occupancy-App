FROM i386/gradle:jdk11
VOLUME gradle-cache:/home/gradle/.gradle
VOLUME /tmp
USER root
ADD . /home/gradle/project
WORKDIR /home/gradle/project
RUN chown gradle:gradle -R /home/gradle
USER gradle
RUN gradle bootJar
RUN mv /home/gradle/project/build/libs/*.jar /home/gradle/project/app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/gradle/project/app.jar"]

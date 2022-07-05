FROM adoptopenjdk/openjdk11
ARG JAR_FILE=tweetysearch-2.7.0.jar
COPY ${JAR_FILE} tweetysearch.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/tweetysearch.jar"]
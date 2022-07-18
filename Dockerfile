FROM adoptopenjdk/openjdk11
ARG JAR_FILE=tweetysearch-2.7.0.jar
COPY target/${JAR_FILE} tweetysearch.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=kubedeployment","/tweetysearch.jar"]

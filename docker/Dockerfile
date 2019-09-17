FROM openjdk:8-jre-slim
ADD build/libs/oauth2-authorization-server-only-*.jar /opt/oauth2-authorization-server-only/
ENTRYPOINT java -jar /opt/oauth2-authorization-server-only/oauth2-authorization-server-only-*.jar
EXPOSE 8080
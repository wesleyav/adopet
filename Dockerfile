FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/adopet

COPY /target/adopet*.jar adopet.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar adopet.jar --spring.profiles.active=${PROFILE}
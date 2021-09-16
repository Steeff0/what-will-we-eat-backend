#Kadaster image contains useful self-signed certificates
FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="Steven Gerritsen <steven.gerritsen@gmail.com>"

# 8080 Web exposure
# 8088 Prometheus metrics exporter
EXPOSE 8080 8088

ENV TZ=Europe/Amsterdam
ENV JVM_OPTS=""

RUN set -x \
    && apk update --no-cache \
    && apk add curl bash bash-completion --no-cache \
    && addgroup -S wwwes \
    && adduser -S wwwes -G wwwes \
    && mkdir -p "/var/log/" \
    && mkdir -p "/opt/webapps/" \
    && ln -s "/var/log/" "/opt/webapps/log"

COPY ./dockerFiles/entrypoint.sh /opt/entrypoint.sh
COPY ./build/libs/wwwe-services.jar /opt/webapps/wwwe-services.jar

RUN set -x \
    && chmod a+rX -R /opt \
    && chmod a+rwt -R /var/log \
    && chmod a+x "/opt/entrypoint.sh" \
    && chmod a+x "/opt/webapps/wwwe-services.jar"

USER wwwes
WORKDIR "/opt/webapps"
ENTRYPOINT ["/opt/entrypoint.sh"]
CMD ["java", "-jar", "wwwe-services.jar"]

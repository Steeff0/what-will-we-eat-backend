# Bulder that creats the JAR
FROM gradle:7.1-jdk11-openj9 AS builder
COPY . /app
RUN set -x \
    && cd /app \
    && ./gradlew bootJar

#Kadaster image contains useful self-signed certificates
FROM adoptopenjdk/openjdk11:alpine-jre

RUN set -x \
    && addgroup -S wwwes \
    && adduser -S wwwes -G wwwes \
    && mkdir -p "/var/log/" \
    && mkdir -p "/opt/webapps/" \
    && ln -s "/var/log/" "/opt/webapps/log" \
    && chown -R wwwes:wwwes /opt \
    && chown -R wwwes:wwwes /var/log

COPY --chown=wwwes:wwwes ./dockerFiles/entrypoint.sh /opt/entrypoint.sh

RUN set -x \
    && chmod +x "/opt/entrypoint.sh"

COPY --from=builder --chown=wwwes:wwwes /app/build/libs/wwwe-services.jar /opt/webapps/

USER wwwes
WORKDIR "/opt/webapps"
ENTRYPOINT ["/opt/entrypoint.sh"]
CMD ["java", "-jar", "wwwe-services.jar"]

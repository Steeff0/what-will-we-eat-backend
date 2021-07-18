# Bulder that creats the JAR
FROM adoptopenjdk/openjdk11:alpine-slim AS builder
RUN set -x && mkdir "/application"
WORKDIR /application/
COPY src/ gradlew build.gradle settings.gradle gradle gradle.properties ./
RUN ./gradlew build

#Kadaster image contains useful self-signed certificates
FROM adoptopenjdk/openjdk11:alpine-jre
ARG APPLICATION_FOLDER=/opt/webapps

RUN set -x \
    && addgroup -S wwwe \
    && adduser -S appuser -G wwwe \
    && mkdir -p "/var/log" \
    && ln -s "/var/log/" "/opt/webapps/log" \
    && chown -R wwwe:wwwe /opt \
    && chown -R wwwe:wwwe /var/log

COPY --chown=wwwe:wwwe ./dockerFiles/entrypoint.sh /opt/entrypoint.sh

RUN set -x \
    && chmod +x "/opt/entrypoint.sh"

COPY --from=builder --chown=wwwe:wwwe /application/build/libs/what-will-we-eat-services.jar /opt/webapps/

USER wwwe
WORKDIR "/opt/webapps"
ENTRYPOINT ["/opt/entrypoint.sh"]
CMD ["java", "-jar", "iv-ngr-services.jar"]

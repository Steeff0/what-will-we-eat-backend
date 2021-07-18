#!/bin/bash

echo ""
echo "Starting container with the following commands: $@"

if [ "$1" == "java" ];
then
    echo ""
    echo "Starting on ${HOSTNAME}."
    echo ""
    shift

    if [ -r "/opt/webapps/config/log4j2.xml" ]; then
      echo "Starting with custom log4j2 xml from the config folder"
      JVM_ARGS="${JVM_ARGS} -Dlog4j.configurationFile=/opt/webapps/config/log4j2.xml"
    fi

    JVM_ARGS="${JVM_ARGS} -Dfile.encoding=UTF8 -Duser.timezone=Europe/Amsterdam"

    if [ "${DEBUG_MODE^^}" == "TRUE" ]; then
      echo "Debugging mode on"
      JVM_ARGS="${JVM_ARGS} -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
    fi

    if [ -r "/opt/setenv.sh" ]; then
      . "/opt/setenv.sh"
    fi

    echo "Command: java ${JVM_ARGS} ${@}"

    exec java ${JVM_ARGS} ${@}
else
    exec $@
fi

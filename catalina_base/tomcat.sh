#!/bin/bash

#ACTION: start | debug | stop
ACTION=$1
if [ -z $ACTION ]; then
    ACTION=""
fi

if [[ $ACTION == "start" ]]; then
    $CATALINA_EXEC start
    sleep 2

    tail -n 100 -f $CATALINA_BASE/logs/catalina.out
elif [[ $ACTION == "debug" ]]; then
    $CATALINA_EXEC jpda start
    sleep 2

    tail -n 100 -f $CATALINA_BASE/logs/catalina.out
elif [[ $ACTION == "stop" ]]; then
    #stop
    $CATALINA_EXEC stop
else 
    echo "Opciones: start | debug | stop"
    exit 1
fi


#!/bin/bash

EXEC_PATH="`dirname \"$0\"`"              # relative
EXEC_PATH="`( cd \"$EXEC_PATH\" && pwd )`"  # absolutized and normalized
if [ -z "$EXEC_PATH" ] ; then
  # error; for some reason, the path is not accessible
  # to the script (e.g. permissions re-evaled after suid)
  exit 1  # fail
fi

# Ajustar estas variables al entorno local
JAVA_HOME="$HOME/.jenv/versions/`jenv version-name`"
CATALINA_HOME=~/Applications/apache-tomcat-9.0.10

PRJ_ROOT=$EXEC_PATH
CATALINA_BASE=$PRJ_ROOT/catalina_base
CATALINA_EXEC=$CATALINA_HOME/bin/catalina.sh

export JAVA_HOME
export CATALINA_BASE
export CATALINA_EXEC

# BEGIN Create folders & symlinks
WEBAPP_SYMLINK=$CATALINA_BASE/ticketing
FOLDERS=( "/temp" "/logs")

if [[ ! -L "$WEBAPP_SYMLINK" ]]; then
    ln -s $PRJ_ROOT/build/exploded $WEBAPP_SYMLINK
fi

for i in "${FOLDERS[@]}"
do
    if [[ ! -d "${CATALINA_BASE}${i}" ]]; then
        mkdir "${CATALINA_BASE}${i}"
    fi
done
# END Create folders & symlinks

# Build project
if [[ $1 != "stop" ]]; then
    $PRJ_ROOT/gradlew explodedWar
fi

$CATALINA_BASE/tomcat.sh $1


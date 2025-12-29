#!/bin/bash

# 主应用名称
APPLICATION="optional-face-recognition"

#杀掉关键进程
PID=$(ps -ef | grep "${APPLICATION}" | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo ${APPLICATION} is already stopped
else
    echo kill ${PID}
    kill ${PID}
    while ps -p $PID > /dev/null
    do
       echo "Waiting for ${APPLICATION} to terminate..."
       sleep 1
    done
    echo ${APPLICATION} stopped successfully
fi

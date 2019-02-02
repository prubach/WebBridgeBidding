#!/usr/bin/env bash
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
#######
PID=`jps | grep WebBridge | cut -f 1 -d " "`
if [ -n "$PID" ]; then
    kill $PID
fi
sleep 2
PID=`jps | grep WebBridge | cut -f 1 -d " "`
if [ -n "$PID" ]; then
    kill -9 $PID
fi
cd $DIR
screen -dmSL bridge java -jar build/libs/WebBridgeBidding*.war

#!/bin/bash
#screen -dmS bridge java -jar build/libs/WebBridgeBidding.war

cd build/libs/
chmod +x WebBridgeBidding.war
java -jar WebBridgeBidding.war



#!/bin/bash
screen -dmS bridge java -jar -Dspring.profiles.active=production build/libs/WebBridgeBidding.war

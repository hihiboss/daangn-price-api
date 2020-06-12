#!/bin/bash

REPOSITORY=/home/ec2-user/app/git
APP=daangn-price-api

echo "> Change working directory"
cd $REPOSITORY/$APP/
echo "> Current working directory: $(pwd)"

echo "> Git Pull"
git pull

echo "> Start building the project"
./gradlew build

echo "> Copy the build files"
cp ./build/libs/*.jar $REPOSITORY/

CURRENT_PID=$(pgrep -f springboot-webservice)
echo "> PID of current running application: $(CURRENT_PID)"

if [ -z $CURRENT_PID ]; then
    echo "> There is no application running"
    echo "> Killing application process is passed"
else
    echo "> kill -2 $CURRENT_PID"
    kill -2 $CURRENT_PID
    sleep 5
fi

echo "> Make JAR file"
./gradlw bootJar

echo "> Build Docker image"
docker build -t hihiboss/daangn-price-api .

echo "> Deploy new application"
docker-compose up -d

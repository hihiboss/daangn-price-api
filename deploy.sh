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

echo "> Make JAR file"
./gradlw bootJar

echo "> Build Docker image"
docker build -t hihiboss/daangn-price-api .

echo "> Kill running application"
docker-compose down

echo "> Deploy new application"
docker-compose up -d

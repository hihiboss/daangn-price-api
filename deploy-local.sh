echo "> Make application JAR file"
./gradlew bootJar

echo "> Build docker file"
docker build -t hihiboss/daangn-price-api .

echo "> Deploy application"
docker-compose up -d


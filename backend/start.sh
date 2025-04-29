docker-compose down

docker rmi microservice-config-server:latest
docker rmi microservice-api-gateway:latest
docker rmi microservice-eureka:latest
docker rmi microservice-tasks-manager:latest

mvn clean install -DskipTests -f ./microservice-config-server/pom.xml
mvn clean install -DskipTests -f ./microservice-eureka/pom.xml
mvn clean install -DskipTests -f ./microservice-api-gateway/pom.xml
mvn clean install -f ./microservice-tasks-manager/pom.xml

docker build -t microservice-config-server:latest -f ./microservice-config-server/Dockerfile ./microservice-config-server
docker build -t microservice-api-gateway:latest -f ./microservice-api-gateway/Dockerfile ./microservice-api-gateway
docker build -t microservice-eureka:latest -f ./microservice-eureka/Dockerfile ./microservice-eureka
docker build -t microservice-inventory:latest -f ./microservice-tasks-manager/Dockerfile ./microservice-tasks-manager

docker-compose up -d

echo "Docker compose up. Waiting to run migrations"
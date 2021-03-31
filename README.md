# docker 打包启动
```
mvn clean package

cd eureka

docker build -t eureka .

cd ../gateway-server

docker build -t gateway .

cd ../server

docker build -t server .

cd ../

docker-compose up -d

```

## 多个服务后事件处理的服务器不一样，导致异步转同步概率出错...

### 解决方案

 分布式锁 redis
 
 提出一个单体的controller 服务 
 
 前端支持异步返回值..
 

## 用什么替换mysql做事件存储

### 解决方案
  
  缓存？？？
  
  mongodb
  
  水平分表
  
  主从


## eureka高可用 springcloud 多节点 docker 部署
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
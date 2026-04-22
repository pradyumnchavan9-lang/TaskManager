Project Name : Task Manager

Simple application used to create and delete daily tasks 

Tech stack (Spring Boot, React, MySQL, Redis, JWT)

Prerequisites (Java 17, Node.js, MySQL, Redis)

Integrated JWT based authentication and role based authorization

Run Backend:-./mvnw spring-boot:run

Run Frontnd:- npm install && npm start

Application Properties Setup

spring.application.name=TaskManager

spring.datasource.url=<your-mysql-url>

spring.datasource.username=<your-mysql-username>

spring.datasource.password=<your-mysql-password>

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

jwt.secret=<your-secret-key>

jwt.expiration=86400000

spring.redis.host=localhost

spring.redis.port=6379

API Documentation: Import postman_collection.json into Postman to test all endpoints

Scaling:-
As the duration for the assignment was limited, it was difficult to create scalable application, but if we want to scale in future, here's what we can do:-

Caching: Current Redis only stores user's information after login in future we can store all tasks of a user in cache as well 

Microservices: Currently our application is monolith, but suppose if we have huge amount of users we might want to switch to microservices where we can separate auth, task, user service with and API gateway

LoadBalancing: If we are entering into Microservices we may need an external Load Balancer like nginx or we can even use Spring cloud's API gateway 

Kafka: Microservices without Kafka or any other messaging queues are useless because without them how will we achieve Asynchronous Communication among services.

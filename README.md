# SPRING SANDBOX

## APLICAÇÃO SPRING BOOT
> Projeto para aplicar todos os conhecimentos adquiridos no dia dia.

# Integração contínua
[![Build Status](https://travis-ci.org/wesleyosantos91/spring-sandbox.svg?branch=master)](https://travis-ci.org/wesleyosantos91/spring-sandbox)

# License
![GitHub](https://img.shields.io/github/license/wesleyosantos91/spring-sandbox?style=for-the-badgex)

# Tecnologias
- Java 14
- Spring Boot 2.3.4.RELEASE
    - spring-boot-starter-web
    - spring-boot-starter-data-jpa
    - spring-boot-devtools
    - spring-boot-starter-validation
    - spring-boot-starter-cache
    - spring-boot-starter-data-redis
    - spring-boot-starter-hateoas
    - spring-cloud-starter-netflix-hystrix (pattern circuit breaker)
    - spring-boot-starter-security
- Swagger
    - springfox-swagger2
    - springfox-swagger-ui
- Flywaydb
- Lombok
- Mysql
- Redis
- Junit 5
- Mockito
- Rest Assured
- testcontainers
- Tomcat (Embedded no Spring Boot)
- Git

# Execução

A execução das aplicações são feitas através do de um comando Maven que envoca a inicialização do Spring Boot.

- Scripts
    ### Executar docker-compose
    - 1° comando: ``` cd src/main/docker/``` 
    - 2° comando: ```docker-compose -f docker-compose.yml up``` 
    ### Executar a aplicação
    -  ```./mvnw clean compile spring-boot:run```
    ### Executar testes
    -  ```./mvnw clean compile verify sonar:sonar```
    
# Utilização

## Swagger
http://localhost:8080/swagger-ui.html

## Sonar
http://localhost:9000/



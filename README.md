# SPRING SANDBOX

## APLICAÇÃO SPRING BOOT
> Projeto para aplicar todos os conhecimentos adquiridos no dia dia.

# License
![GitHub](https://img.shields.io/github/license/wesleyosantos91/spring-sandbox?style=for-the-badgex)

# Tecnologias
- Java 14
- Spring Boot 2.3.4.RELEASE
    - spring-boot-starter-web
    - spring-boot-starter-data-jpa
    - spring-boot-devtools
    - spring-boot-starter-validation
    - spring-boot-starter-hateoas
- Flywaydb
- Lombok
- Mysql
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

## Sonar
http://localhost:9000/



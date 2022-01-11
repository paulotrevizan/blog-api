# BLOG API

Uma simples API Restful em Java 8 / Spring Boot 2.6.2

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
* Java JRE/JDK 8
* PostgreSQL 
* Maven 3.6.3~

## ☕ Usando BLOG API

Para usar o BLOG API, siga estas etapas:

Rodar Docker com o Postgres
```
docker pull postgres
docker run --name postgres-local -e "POSTGRES_PASSWORD=Postgres" -p 5432:5432 -d postgres
```

Acessar por linha de comando a pasta raíz do projeto e executar os seguintes comandos
```
mvn clean install
java -jar -Dserver.port=8080 target/blog-api-0.0.1-SNAPSHOT.jar
```

[⬆ Voltar ao topo](#nome-do-projeto)<br>

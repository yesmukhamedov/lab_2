# Lab 2 - Users CRUD (Spring Boot + PostgreSQL)

Minimal Spring Boot 3 (Java 17) MVC CRUD for a single `User` entity using one controller and one Thymeleaf view.

## Prerequisites

- Java 17
- Maven 3.9+
- PostgreSQL 13+

## Database setup

```sql
CREATE DATABASE lab2_db;
CREATE USER lab2 WITH PASSWORD 'lab2';
GRANT ALL PRIVILEGES ON DATABASE lab2_db TO lab2;
```

## Configuration

`src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lab2_db
spring.datasource.username=lab2
spring.datasource.password=lab2
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.mvc.hiddenmethod.filter.enabled=true
```

## Run the application

```bash
mvn spring-boot:run
```

Open: http://localhost:8080/users

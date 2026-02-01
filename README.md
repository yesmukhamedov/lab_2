# Lab 2 - Spring Boot CRUD with PostgreSQL

This project is a clean Spring Boot 3 (Java 17) CRUD example that provides **both**:
- MVC CRUD (Thymeleaf pages)
- REST CRUD (JSON API)

Entity: `Student`

## Prerequisites

- Java 17
- Maven 3.9+
- PostgreSQL 13+

Create database and user:

```sql
CREATE DATABASE lab2_db;
CREATE USER lab2 WITH PASSWORD 'lab2';
GRANT ALL PRIVILEGES ON DATABASE lab2_db TO lab2;
```

## Configuration

Database configuration is in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lab2_db
spring.datasource.username=lab2
spring.datasource.password=lab2
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
```

## Run the application

```bash
mvn spring-boot:run
```

App runs at `http://localhost:8080`.

## MVC URLs (Thymeleaf)

- `GET /students` - list students
- `GET /students/new` - create form
- `POST /students` - submit create
- `GET /students/{id}/edit` - edit form
- `POST /students/{id}` - submit update
- `POST /students/{id}/delete` - delete

## REST API (JSON)

Base path: `/api/students`

### Create

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Ada Lovelace","email":"ada@example.com","enrollmentYear":2022}'
```

### List

```bash
curl http://localhost:8080/api/students
```

### Get by ID

```bash
curl http://localhost:8080/api/students/1
```

### Update

```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Ada Lovelace","email":"ada@example.com","enrollmentYear":2023}'
```

### Delete

```bash
curl -X DELETE http://localhost:8080/api/students/1
```

## Project structure

```
src/main/java/kz/iitu/lab2
├── Lab2Application.java
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
└── validation
```

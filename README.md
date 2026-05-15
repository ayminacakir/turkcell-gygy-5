# Turkcell GYGY 5.0 Java 

This repository collects Java practice projects and exercises from the Turkcell GYGY 5.0 training track. It is a workspace of small Java applications, Spring Boot demos, and CQRS examples that were built while learning Java 21, Spring Boot, JWT-based security, and request/handler based backend structure.

## What's inside

- `intro/`
  - Java basics and small console examples
  - Contains simple classes like `Main`, `OOP`, `Functions`, and `Interfaces`
  - Good starting point for object-oriented programming, loops, functions, and basic data handling

- `bankingapplication/`
  - Maven-based Java 17 console banking exercise
  - Core features: login/registration, account viewing, deposit, withdrawal, transfer
  - Uses in-memory repositories and demo data for quick testing
  - Entry point: `com.turkcell.Main`

- `library/`
  - Spring Boot sample service with a classic `LibraryApplication`
  - Used for practicing basic Spring Boot project structure
  - Includes simple REST and data layer examples

- `library-cqrs/`
  - Spring Boot 4 application with CQRS-style organization
  - Includes JWT and Spring Security dependencies
  - Built as a learning project for separating command/query flows in a web app

- `spring-cqrs/`
  - Another Spring Boot 4 REST application with CQRS and JWT authorization
  - Focuses on authentication, role-based access control, and request pipeline protection
  - Includes JPA, PostgreSQL runtime driver, and JWT handling libraries

- `spring-starter/`
  - Starter Spring Boot project for learning annotations and basic app wiring
  - Contains a simple `SpringStarterApplication` with comments explaining Spring concepts

## Java and build settings

- `bankingapplication` targets Java 17
- `intro`, `spring-cqrs`, `library-cqrs`, `spring-starter` and `library` target Java 21
- Each project is a standalone Maven module with its own `pom.xml`
- Spring projects use Spring Boot 4.0.6

## Running projects

### Spring Boot example (`spring-cqrs` or `library-cqrs`)
```bash
cd spring-cqrs
mvn clean compile
mvn spring-boot:run
```

## What I learned here

- Java application structure and Maven project layout
- Basic console user flows, input/output, and service logic
- Spring Boot application startup and dependency configuration
- JWT authentication and authorization filters
- Role-based access control and custom exception handling
- CQRS-style request/handler separation in Spring
- How to combine JPA, security, and REST in a training project





# ChâTop back-end application
ChâTop is a portal for connecting potential tenants and owners for rentals.

This application is the back-end of ChâTop.

It is developed using Spring Boot 3.2.0 framework.

## Prerequisites
Java 17

Gradle 8.5

MySQL 8.0.35

## Installation
First install [Java](https://www.oracle.com/fr/java/technologies/downloads/#java17) according your operating system.

Then install [Gradle](https://gradle.org/install/).

And finally install [MySQL](https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/).

Go to `sql` in the root directory and :
* open a terminal,
* connect to mysql with your credentials,
* create a database `CREATE DATABASE chatop;`,
* import the SQL script `SOURCE script.sql;`.

Open `src/main/resources/application-prod.properties` and update `spring.datasource.username` and `spring.datasource.password` with your MySQL credentials.

## Run
In the root directory execute `gradle build` to build the archive.

Two points are important.

First, 2 profiles are set: 
* `dev` for development with in-memory database,
* `prod` for production with MySQL database.

Spring default profile is set to `dev`.

The second point is that you have to create an `uploads` directory where the archive is located.

In our case, go to `build/libs`, create `uploads` directory and run `java -Dspring.profiles.active=prod -jar chatop-backend-1.0.0.jar`.

You can use an app like Postman to test the endpoints.

## Folder structure
```bash
├── build
├── gradle
├── sql
├── src
│   ├── main
│   │   ├── java/com/tipikae/chatop
│   │   │  ├── configuration
│   │   │  ├── controllers
│   │   │  ├── dto
│   │   │  │   ├── message
│   │   │  │   │   └── converter
│   │   │  │   ├── rental
│   │   │  │   │   └── converter
│   │   │  │   └── user
│   │   │  │       └── converter
│   │   │  ├── errorHandler
│   │   │  ├── exceptions
│   │   │  │   ├── rental
│   │   │  │   ├── storage
│   │   │  │   └── user
│   │   │  ├── models
│   │   │  ├── repositories
│   │   │  └── services
│   │   │      ├── auth
│   │   │      ├── message
│   │   │      ├── rental
│   │   │      ├── storage
│   │   │      └── user
│   │   └── resources
│   └── test/java/com/tipikae/chatop
│       └── it
│           ├── repository
│           └── service
└── uploads
```
## Tests
Navigate to `build/reports/tests/test/index.html` to see the tests results.
## Documentation
### Javadoc
Execute `gradle javadoc` to generate the documentation.

Navigate to `build/docs/javadoc/index.html` to browse the documentation.
### Swagger
To see the API documentation, when this app is launched, in your web browser go to `http://localhost:3001/api/swagger-ui/index.html`
## Author
Gilles BERNARD (@tipikae)

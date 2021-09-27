# Tennis Game API
This is a proposed implementation of the Tennis Game kata using Test Driven Development (TDD) with Spring Boot and JUnit.

Complete Description of this kata can be found here: http://codingdojo.org/kata/Tennis/

# Stack

- Java 11
- Spring Boot 2.5.5
- Lombok
- JUnit 5
- Mockito
- Spring Data JPA
- H2 in-memory database
- Maven
- Swagger/OpenApi 3.0

# How to Run It
This application will be packaged as a jar which have Tomcat embedded, you can follow these steps to run it: 
1. Clone this repository:

        git clone https://github.com/AbdelMadjid-Melbous/2021-DEV1-090.git
2. Make sure you have JDK 11 and maven
3. Build and package the application using the following maven command: 

        mvn clean install
4. Move to /target and run the following command:

        java -jar tennisgamekata-0.0.1-SNAPSHOT.jar
        
Alternatively, you can run the app without packaging it using: 

    mvn spring-boot:run

Or you can run it with your preferred IDE executing the main method of TennisGameKataApplication.class.
     
The app will start running at http://localhost:8080.

# Explore Game APIs

This application defines the following endpoints: 

- Create a new Tennis Game:

        POST /api/tennis-game/new
- Score a point for a player in a Tennis game:

        POST /api/tennis-game/{gameId}/score
 - Quit a Tennis game
        
        POST /api/tennis-game/{gameId}/quit
        
All APIs are "self-documented" by OpenApi 3.0 using annotations. To view the API docs, run the application and browse to: http://localhost:8080/swagger-ui.html

You can test them directly from this Swagger UI or via Postman or any other rest client.

**Note:** The updated Game score and results will be found in the response of each api call and in the console/Terminal or IDE console.

# Run Tests

You can run tests using one of the following options: 

- Using the maven command:

        mvn test
- Using your preferred IDE: running all tests or one by one.
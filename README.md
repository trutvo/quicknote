# quicknote

quicknote is java spring boot demo application for note-taking.

## Pre-requisites

You need the following software installed on your machine:

* Java SDK 21
* docker
* docker-compose
* curl

## How to run 

### Run in the IDE

Execute the class `QuicknoteApplication`

To check if the server is up you can call the health endpoint:

http://localhost:8080/actuator/health

### Run in console

To run quicknote as single process in console you have to start the database container first:

    docker-compose up -d

After that you have to run quicknote with the profile `test`.

    ./mvnw spring-boot:run -Dspring.profiles.active=test 

Or compile and run it:

    ./mvnw clean package
     java -jar -Dspring.profiles.active=test target/quicknote-0.0.1-SNAPSHOT.jar

## The API

List all notes

    curl http://localhost:8080/notes

Get one note:

    curl http://localhost:8080/notes/root

Create or update a note:

    curl -H "Content-Type: application/json" http://localhost:8080/notes/hello -d "hello"

Delete a note:

    curl -X DELETE http://localhost:8080/notes/hello

## Integration tests

There is an integration test for the API endpoints:

     ./src/test/scripts/integration-test.sh

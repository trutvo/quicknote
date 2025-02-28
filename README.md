# quicknote

quicknote is java spring boot demo application for note-taking.

## How to run

Execute class `QuicknoteApplication`

or run maven command

    ./mvnw spring-boot:run

To check if the server is up you can call the health endpoint:

http://localhost:8080/actuator/health

## API

List all notes

    curl http://localhost:8080/notes

Get one note:

     curl http://localhost:8080/notes/root

Create or update a note:

    curl -H "Content-Type: application/json" http://localhost:8080/notes/hello -d "hello"

Delete a note:

    curl -X DELETE http://localhost:8080/notes/hello
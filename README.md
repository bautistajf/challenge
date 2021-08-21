# Backend

This project was generated with Java 11 and Springboot 2.3.5.RELEASE.

## Development server

Navigate to `http://localhost:8081/`.

## Collaborator services

The list of all collaborator.

```
curl --location --request GET 'http://localhost:8081/collaborators-srv/collaborators'
```

The get collaborator by id

```
curl --location --request GET 'http://localhost:8081/collaborator-srv/collaborators/1'
```

Create a new collaborator

```
curl -X POST 'http://localhost:8081/collaborator-srv/collaborators' \
 -H 'accept: application/json' \
 -H 'Content-Type: application/json' \ 
 -d '{ \'address\': \'Mateu Monserrat\', \'city\': \'Mallorca\', \'email\': \'test2@gmail.com\', \'lastName\': \'Bautista\', \'name\': \'Javier\', \'phoneNumber\': \'68126588\'}'
```

Update a collaborator

```
curl -X PUT 'http://localhost:8081/collaborator-srv/collaborators/2' \  
-H 'accept: application/json' -H 'Content-Type: application/json' \ 
-d '{ \'address\': \'Mateu Monserrat\', \'city\': \'Mallorca\', \'email\': \'string@gmail\', \'id\': 1, \'lastName\': \'Bautista\', \'name\': \'Javier\', \'phoneNumber\': \'683126519\'}'
```

Delete one collaborator by id

```
curl --location --request DELETE 'http://localhost:8081/collaborator-srv/collaborators/1'
```

## Swagger

http://localhost:8081/collaborator-srv/swagger-ui.html#/

## Management

http://localhost:8082/collaborator-srv/management

## Test

gradle clean test

## Sonar

gradle clean -Dsonar.host.url=http://localhost:9000 sonarqube


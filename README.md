#Insurance Management Platform

This is an Insurance Management Platform built using Spring Boot and Java. The platform allows users to manage insurance policies, clients, and claims.


#Project Structure

The project follows a standard Spring Boot project structure with the following packages:

com.rushi.insurance: the main package containing the Application class and the configuration classes.
com.rushi.insurance.controller: contains the REST API controllers.
com.rushi.insurance.exception: contains custom exception classes and exception handlers.
com.rushi.insurance.model: contains the domain models.
com.rushi.insurance.repository: contains Spring Data JPA repositories for the domain models.
com.rushi.insurance.service: contains the business logic services.
com.rushi.insurance.service.impl: contains implementation of services.
com.rushi.insurance.validators: 


#Features
The platform provides the following features:

1. Client:

GET /api/clients: Fetch all clients.
GET /api/clients/{id}: Fetch a specific client by ID.
POST /api/clients: Create a new client.
PUT /api/clients/{id}: Update a client's information.
DELETE /api/clients/{id}: Delete a client.

2.Insurance Policy:
GET /api/policies: Fetch all insurance policies.
GET /api/policies/{id}: Fetch a specific insurance policy by ID.
POST /api/policies: Create a new insurance policy.
PUT /api/policies/{id}: Update an insurance policy.
DELETE /api/policies/{id}: Delete an insurance policy.

3.Claim:

GET /api/claims: Fetch all claims.
GET /api/claims/{id}: Fetch a specific claim by ID.
POST /api/claims: Create a new claim.
PUT /api/claims/{id}: Update a claim's information.
DELETE /api/claims/{id}: Delete a claim.


#How to Run Locally

1.Clone the project from GitHub.
2.Open the project in your preferred IDE.
3.Run the project as a Spring Boot application.
4.The API endpoints can be accessed at http://localhost:8080/api/ using a tool like Postman.
5.To access the H2 database console, go to http://localhost:8080/h2-console/ and enter the following JDBC URL: jdbc:h2:mem:testdb. The default username is sa and there is no password.


#Dependencies

The following dependencies are used in the project:

Spring Boot
Spring Data JPA
H2 Database
Spring Web


# Validation and Exception Handling

The platform uses Spring Boot's built-in validation and exception handling. Custom exception classes have been created for handling bad requests, not found exceptions, and general runtime exceptions. The validation is done using annotations on the domain models, and error messages are returned in a standard format.

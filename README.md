# Insurance Management Platform

This is an Insurance Management Platform built using Spring Boot and Java. The platform allows users to manage insurance policies, clients, and claims.


# Project Structure

The project follows a standard Spring Boot project structure with the following packages:

1. com.rushi.insurance: the main package containing the Application class and the configuration classes.
2. com.rushi.insurance.controller: contains the REST API controllers.
3. com.rushi.insurance.exception: contains custom exception classes and exception handlers.
4. com.rushi.insurance.model: contains the domain models.
5. com.rushi.insurance.repository: contains Spring Data JPA repositories for the domain models.
6. com.rushi.insurance.service: contains the business logic services.
7. com.rushi.insurance.service.impl: contains implementation of services.
8. com.rushi.insurance.validators: contains defination and implementation of validation rules for the input data provided in the API requests


# Features
The platform provides the following features:

1. Client:

i. GET /api/clients: Fetch all clients.
ii. GET /api/clients/{id}: Fetch a specific client by ID.
iii. POST /api/clients: Create a new client.
iv. PUT /api/clients/{id}: Update a client's information.
v. DELETE /api/clients/{id}: Delete a client.

2.Insurance Policy:
i. GET /api/policies: Fetch all insurance policies.
ii. GET /api/policies/{id}: Fetch a specific insurance policy by ID.
iii. POST /api/policies: Create a new insurance policy.
iv. PUT /api/policies/{id}: Update an insurance policy.
v. DELETE /api/policies/{id}: Delete an insurance policy.

3.Claim:

i. GET /api/claims: Fetch all claims.
ii. GET /api/claims/{id}: Fetch a specific claim by ID.
iii. POST /api/claims: Create a new claim.
iv. PUT /api/claims/{id}: Update a claim's information.
v. DELETE /api/claims/{id}: Delete a claim.


# How to Run Locally

1.Clone the project from GitHub.
2.Open the project in your preferred IDE.
3.Run the project as a Spring Boot application.
4.The API endpoints can be accessed at http://localhost:8080/api/ using a tool like Postman.
5.To access the H2 database console, go to http://localhost:8080/h2-console/ and enter the following JDBC URL: jdbc:h2:mem:testdb. The default username is sa and there is no password.


# Dependencies

The following dependencies are used in the project:

1. Spring Boot
2. Spring Data JPA
3. H2 Database
4. Spring Web
5. Vaildation


# Validation and Exception Handling

The platform uses Spring Boot's built-in validation and exception handling. Custom exception classes have been created for handling bad requests, not found exceptions, and general runtime exceptions. The validation is done using annotations on the domain models, and error messages are returned in a standard format.

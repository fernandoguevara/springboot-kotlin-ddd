# Spring Boot Kotlin Domain Driven Design Example

Just an example project where ddd is implemented with some other great patterns and architecture from eShopOnContainers

The use case its just a simple web api which saves notes in a database and send some emails when they are created.

## Architecture
* Spring Boot 2.5.1
* Kotlin
* Domain driven design
* Mediator Pattern
* CQRS Pattern (Commands and Queries)
* Swagger API documentation
* Flyway Migrations
* Multiple Database Vendors (specifically sql server and postgres)
* Domain Events
* Hibernate Validation
* Keycloak JWT User Roles for Web API Validation
* Pipeline Registration
* Dockerfile to create Docker Images
* Swagger Implicit Authentication with Keycloak

## How to Run
Basically you need to create a database(sql server or postgres), keycloak, elasticsearch , kibana if you want data visualization.

You can use the Vagrantfile if you use got Vagrant installed on your machine, it's nice and easy to use for demo projects, the virtual machine will need at least 3-4GB RAM if you intend to use kibana and elasticsearch.

## DISCLAIMER!
It's just a demo project , not intended to use in production, remember theres no silver bullet.

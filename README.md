# Spring Boot Kotlin Domain Driven Design Example

Just an example project where ddd is implemented with some other great patterns and architecture  from eShopOnContainers

The use case its just a simple web api who saves notes in a db and send some emails when they are created.


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

## Usage
Basically you need to create a database(sql server or postgres), keycloak, elasticsearch , kibana if you want data visualization and of course run the project or create and run a docker image.

You can use the Vagrantfile if you use Vagrant in your machine, its nice and easy to use for demo projects, the vm will need at least 3-4GB RAM if you intend to use kibana and elasticsearch.

## DISCLAIMER!
Its just a demo project , not intended to use in production or following the best architecture, remember theres no silver bullet.
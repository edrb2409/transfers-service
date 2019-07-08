# Transfers Service

The project is a sample project for managing transactions between pre-registered customer's accounts 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- Java 8

### Build

This project is build using maven, to ensure a repeatable build process we also use the included maven wrapper. Every
gradle command should instead be executed as ./mvnw to use the wrapper instead of your installed maven.

The command to build the project is:

```
./mvnw clean package
```

## Running the test cases

Run the test cases execute

```
./mvnw clean test
```

### Deploy

#### Manually

The following are the steps required in compiling the project and executing the jar.


###### Compiling the project
* Run ```./mvnw clean package``` to first build the jar


###### Running the project

* Run ```java -jar target/transfers-service-1.0-SNAPSHOT.jar```

## Build With

- [Eclipse Jetty](https://www.eclipse.org/jetty/)
- [Jersey](https://jersey.github.io/)
- JUnit 4.12
- Mockito
- Maven

## Author

- Daniel Ron - [edrb-profile](https://edrb.github.io)

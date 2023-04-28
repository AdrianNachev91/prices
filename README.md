# Prices API

## Requirements

For building and running the application you need:

- [JDK 20](https://www.oracle.com/nl/java/technologies/javase/jdk20-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.inditex.prices.PricesApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Brief description

This API handles Prices table, currently the only endpoint fetches price list given a brand name, product id and date.

## Components

H2 Database is used for data storage in in-memory mode

Swagger link once the API is running: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). 
You can test the API through swagger. Alternatively (since it's only a get endpoint at the moment) you can
directly put URL in the browser or use postman.

Controller advice is used for error handling.

Swagger annotations are used for documentation.

## Assumptions
1. PRICE_LIST is the primary key as the only unique key in the table. Not specified in the assignment
2. String identifier is a value from Brands table which has Many-To-One relationship with Prices. I was not entirely clear what else this could be in the assignment.
3. For the test cases I assumed 14th, 15th day etc. mean from June as the most logical test case given the assignment data

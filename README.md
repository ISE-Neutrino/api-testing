# APIs Testing

This repository demonstrates by example how to make APIs End-to-End(e2e) testing using Http files and Rest Client.

## Prerequisites

- [Java 17](https://learn.microsoft.com/en-us/java/openjdk/install)
- [Maven (v3.8.8 or higher)](https://maven.apache.org/install.html)
- [Node.js (v20.10.0)](https://nodejs.org/en/download/)

> **Note:** If you are using dev-container, you don't need to install any of the above. Just open the project in VSCode and it will install all the required tools for you.

## Documentation

- [E2E Testing](docs/e2e-test.md)

## Running

Before running the application, make sure you have 
- Installed the Pre-requisites mentioned above.
- Created the environment file `.env` under root folder based on the template file [.env.template](.env.template).
- the environment file [http-client.env.json](e2e-test/http-client.env.json) under `e2e-test` folder is up to date.

To build the service source code and run unit-tests, use:
- `mvn clean install` or `make build`

To run the service in development mode, use:
- `mvn spring-boot:run` or `make run`

 The following is a list of make commands should be run from root folder.

```bash
help                   💬 This help message :)
build                  🔨 build the application and run unit-test 
run                    🏃 Run the application
e2e-init               🔨 Initialize e2e test environment by installing httpyac CLI
e2e-local              💻 Run e2e-test on your local environment
e2e-dev                💻 Run e2e-test on Backbase-msft-dev environment
```

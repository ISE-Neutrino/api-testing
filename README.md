# APIs Testing

This repository shows how to use Http files and Rest Client to automate the end-to-end (e2e) testing of APIs.

## Prerequisites

- [Java 17](https://learn.microsoft.com/en-us/java/openjdk/install)
- [Maven (v3.8.8+)](https://maven.apache.org/install.html)
- [Node.js (v20.10.0+)](https://nodejs.org/en/download/)

> **Note:** If you are using dev-container, you don't need to install any of the above. Just open the project in VSCode and it will install all the required tools for you.

## Documentation

- [E2E Testing using Http files and Rest Client](docs/e2e-test.md)
- [Load testing using K6](docs/load-test.md)

## Running

Before running the application, make sure you have 
- Installed the Pre-requisites mentioned above.
- Created the environment file `.env` under root folder based on the template file [.env.template](.env.template).
- the environment file [http-client.env.json](e2e-test/http-client.env.json) under `e2e-test` folder is up to date.

To build the service source code and run unit-tests, use:
-  `make build` or `mvn clean install`

To run the service in development mode, use:
- `make run` or `mvn spring-boot:run` 

 The following is a list of make commands should be run from root folder.

```bash
help                   💬 This help message :)
build                  🔨 build the application and run unit-test 
run                    🏃 Run the application
e2e-init               🔨 Initialize e2e test environment by installing httpyac CLI
e2e-local              💻 Run e2e-test on your local environment
e2e-dev                💻 Run e2e-test on Backbase-msft-dev environment
```
## Run e2e test

To run e2e test, you need to 

- Install [httpyac] CLI tool by running `make e2e-init` or `npm install -g httpyac`
- Run the API service in dev mode by running `make run` or `mvn spring-boot:run`
- Run the e2e test by running `make e2e-local` or `httpyac testing/e2e-test/*.http --all -e local -o response`



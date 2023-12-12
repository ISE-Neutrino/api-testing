# APIs testing using HTTP files and Rest Client

![testing-pyramid](./images/testing-pyramid.png)

## Context

In our recent engagement, after completing the initial iteration of the API endpoints implementation, We needed to test the API endpoints end-to-end to ensure that the APIs functioned  as intended, and to enable developers to demonstrate their work after every sprint. We evaluated different tools and decided to use HTTP files with HTTP/Rest Client for this task.

Our selection criteria:

- Small learning curve and ease of use
- IDE integration (Visual studio, VS Code, IntelliJ)
- CLI availability for Automation and CI/CD integration
- Compatibility with our inner dev loop (PRs, Code review, source control, etc.)
- Keep the testing process simple and easy to maintain
- Readability  
- Full support for HTTP Specs 
- Authentication support
- Environment variables
- Response validation and handling

This document walks you through the steps for performing an end-to-end test of the API endpoints in this solution.
In this context, we have developed mock Customer API endpoints using Spring Boot. We used this setup to illustrate the process of conducting end-to-end testing with [VS Code](https://code.visualstudio.com/) and  [Httpyac](https://marketplace.visualstudio.com/items?itemName=anweber.vscode-httpyac) extension.

Please find all the code and documentation in the repository [here](https://github.com/ISE-Neutrino/api-testing)

## E2E(end-to-end) testing

Let's start by defining what is end-to-end testing and why we need to do it.

End-to-end testing is a methodology used to test whether the flow of an application is performing as designed from start to finish. The purpose of carrying out end-to-end tests is to identify system dependencies and to ensure that the right information is passed between various system components and systems.

This article will focus on a comprehensive end-to-end testing of the API endpoints, and avoid going into details about the end-to-end testing methodology and different strategies that can be used to do it.

![e2e-testing](./images/e2e-testing.png)

## HTTP files

It is a file with a `.http` extension that allows you to write HTTP requests using a format that follows the standards of [RFC 9110 HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110.html). The file can be executed from IDEs like Visual Studio, VS Code, IntelliJ (natively or through HTTP clients extensions [Rest Client, Httpyac, Intellj HTTP Client]) or from the command line using the ([Httpyac CLI](https://httpyac.github.io/guide/installation_cli.html) - [HTTP Client CLI](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html)).

IDEs integration allows you to send HTTP requests and view the response directly in Code editor without switching to a browser or a separate application.

As it is a text file, it can be easily integrated with the existing development process (PRs, code review, source control, etc.)

## Environment variables

Each IDE or Extension has its own way to define environment variables, but the concept is the same. Environment variables are used to define variables that can be used in the request URL, headers, and body. It is very useful to define the base URL of the API endpoints and the authentication token.

For Httpyac extension in VS Code, you use different sources of the environment variables:
 - System environment variables
 - Json based files and you can define multiple environments per file

 ![Environment-variables](./images/http-files-env.jpg)

## Create HTTP request test

As presented by the image below, the HTTP request is composed of these parts:

- [Request line:](https://httpyac.github.io/guide/request.html#request-line) The first line of the request contains the HTTP method, the URL, and the HTTP version.
- [Headers:](https://httpyac.github.io/guide/request.html#headers) The request headers are used to pass additional information about the request, and about the client itself, to the server.
- [Body:](https://httpyac.github.io/guide/request.html#request-body) The request body is used to send data to the server.
- Metadata: Metadata is used to add additional information about the request, such as request name, title, etc.
- [Response Handling:](https://httpyac.github.io/guide/assert.html) The response handling section is used to write the required logic for response validation and handling.

![http-request-format](./images/http-files-sample.jpg)

 ## Execute the HTTP request

 Httpyac extension allows you to select the environment variables scope and use the `send` UI command to execute the HTTP request.

 
![http-request-run](./images/http-files-run.jpg)

## Response handling and validation

The HTTP Client supports a mechanism to manipulate the response and validate it, by exposing the response as an object that can be used to access its response status, headers, and body.

In addition to a handy utility functions that can be used to validate and handle the response, these functions are exposed by an object called `client` and methods examples (client.test, client.assert,client.global.set, etc.)

![http-request-response](./images/http-files-response-access.jpg)

## Test Automation 

The HTTP files can be executed from the command line using the [Httpyac CLI](https://httpyac.github.io/guide/installation_cli.html) or [HTTP Client CLI](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html). This allows you to automate the test execution and can be integrated with CI/CD pipelines.


```bash
# Install Httpyac CLI
npm install -g httpyac

# Execute the HTTP file
# https://httpyac.github.io/guide/installation_cli.html
httpyac testing/e2e-test/customers.http --all -e local -o response
```

![http-request-run](./images/http-files-run-cli.jpg)

The sample repository contains `make commands` as shorthand to execute the HTTP files using the Httpyac CLI automatically.

```bash
# Install Httpyac CLI
make e2e-init

# Execute the HTTP file
make e2e-local
```

## Conclusion

This article has shown how to do end-to-end testing of API endpoints using HTTP files and Rest Clients. We used the Httpyac extension for VS Code to write and run HTTP requests. We also showed how to use environment variables to set the base URL and the authentication token for the API endpoints. Moreover, we explained how to handle and validate the responses.

## References

- [ISE E2e-testing](https://microsoft.github.io/code-with-engineering-playbook/automated-testing/e2e-testing/)
- [API Testing Tools](https://www.accelq.com/blog/api-testing-tools/)
- [Visual Studio Http files support](https://learn.microsoft.com/en-us/aspnet/core/test/http-files?view=aspnetcore-8.0)
- [VSCode Rest Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client)
- [VSCode Httpyac Rest Client](https://marketplace.visualstudio.com/items?itemName=anweber.vscode-httpyac)
- [Httpyac CLI](https://httpyac.github.io/guide/installation_cli.html)
- [IntelliJ Http Client](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html)



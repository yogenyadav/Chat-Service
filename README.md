## Chat-Service

### Test
Run the Main.class to test the service.

### Service implementation
Implementation is in com.atlassian.hipchat.service.impl.ChatServiceImpl which is called from AWS lambda ChatServiceLambda.

### Frameworks/libraries
- Google Guice is used for dependency injections.
- Junit, Mocito and Hamcrest for unit tests.
- OKHttp is used for http requests.
- Swagger is used for REST API specification.
- AWS APIG and Lambda is used as API execution environment.



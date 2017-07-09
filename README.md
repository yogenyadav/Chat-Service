## Chat-Service

### Unit tests
mvn clean install && mvn test

### Integration tests
mvn clean install && mvn exec:java -e

### Service implementation
Implementation is in com.atlassian.hipchat.service.impl.ChatServiceImpl which is called from AWS lambda ChatServiceLambda.

### Frameworks/libraries
- Google Guice is used for dependency injections.
- Junit, Mocito and Hamcrest for unit tests.
- OKHttp is used for http requests.
- Swagger is used for REST API specification.
- AWS APIG and Lambda is used as API execution environment.



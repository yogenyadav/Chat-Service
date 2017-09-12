### Chat-Service

#### Unit tests
```
mvn clean install 
mvn test
```

#### Integration tests
```
mvn clean install
mvn integration-test
```

#### Service implementation (2 runtimes)
1. Implementation is in com.atlassian.hipchat.service.impl.ChatServiceImpl which is called from AWS lambda ChatServiceLambda.
2. Using spring boot.

#### Frameworks/libraries
- Google Guice is used for dependency injections.
- Junit, Mocito and Hamcrest for unit tests.
- OKHttp is used for http requests.
- Swagger is used for REST API specification.
- AWS APIG and Lambda is used as API execution environment.
- Spring Boot with embeded tomcat for standalone Restful server.

#### Run and test
```
$java -jar target/chatservice-1.0-SNAPSHOT.jar
```
```
Request:
curl localhost:8080/chat/contents?message=%22some%20text%20%40chris%20%40matt%20some%20text%20%28megusta%29%20%28coffee%29%20some%20text%20http%3A%2F%2Fwww.baeldung.com%2Fguide-to-okhttp%22
Response:
{"emoticons":["megusta","coffee"],"mentions":["chris","matt"],"links":[{"title":"A Guide to OkHttp | Baeldung","url":"http://www.baeldung.com/guide-to-okhttp"}]}
```


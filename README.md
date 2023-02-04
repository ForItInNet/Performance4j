
# Performance4j 

[![Apache-2.0 License](https://img.shields.io/badge/License-Apache-green.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

Performance4j library was created to help developers identify places where they can lose performance. This library can measure the time of execution of necessary code parts and save this data. After that, you can work with Performance4j's API to create a statistic board.


## Getting it
The project is based on Java 17+ and Spring Boot 3.x.x

### Importing library

For Gradle
```groovy
dependencies {
   implementation 'com.foritinnet:performance4j:0.0.1-SNAPSHOT'
}
```

For Maven
```xml
<dependency>
  <groupId>com.foritinnet</groupId>
  <artifactId>performance4j</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
## Configuration properties

There are useful configurations that you can set up.

| Configuration                        | Description                            | Value type        | Default value |
| ------------------------------------ | -------------------------------------- | ----------------- | ------------- |
| performance4j.history.enabled        | To enable the saving of the history    | java.lang.Boolean | False         |
| performance4j.history.size           | To set the size of the history stack   | java.lang.Integer | 50            | 
| performance4j.history.datasource.url | To set a database URL (if the null then an in-memory database will be used) | java.lang.String | null |
| performance4j.history.datasource.username | To set a database username        | java.lang.String | null |
| performance4j.history.datasource.password | To set a database password        |  java.lang.String | null |

## Getting Started
To enable the Performance4j you need to add `@EnablePerformanceChecker` to your configuration class.
```java
@Configuration
@EnablePerformanceChecker
public class DemoConfiguration {

}
```

To start checking the performance you just need to add the `@PerformanceChecker` annotation on the method or class levels. You can specify the `group` attribute in this annotation and the `name` attribute. 
If the `group` is null or empty then the bean name will be taken.
If the `name` is null or empty then the method name will be taken.

```java
@Component
public class DemoClass {

    @PerformanceChecker(group = "group", name = "name")
    public void demoMethod() {

    }
}
```

If you use `@PerformanceChecker` on the class level then it will check the performance of all public methods. In this scenario, you can set the `group` attribute to set the default group for these methods.

```java
@Component
@PerformanceChecker(group = "group")
public class DemoClass {

    public void demoMethod() {

    }
}
```
## API Reference (it will work only if history is enabled)

#### Get all groups with performance statistic

```http
  GET performance4j/history/{group}
```



## License

[Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)


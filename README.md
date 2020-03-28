##  Spring Rest Consumption

##Spring 5 - WebClient Example

####Client Application using WebClient (Blocking)
```$xslt
public String getResponseFromURLBlocking() throws InterruptedException {
    /*
        The method bodyToMono() extracts the body to a Mono instance.
        The method Mono.block() subscribes to this Mono instance and
        --> blocks until the response is received <--.
    */
    System.out.println("Initiating HTTP Request:-");

    WebClient webClient = WebClient.create("https://gturnquist-quoters.cfapps.io/api/random");
    Mono<String> result = webClient.get()
            .retrieve()
            .bodyToMono(String.class);
    String response = result.block();
    System.out.println(response);

    //Last line of code to run
    System.out.println("Code to run after HTTP Request");

//        return response;
}
```
The method bodyToMono() extracts the body to a Mono instance. The method Mono.block() subscribes to this Mono instance and blocks until the response is received.

####Client Application using WebClient (Blocking)
```$xslt
public static void getResponseFromURLNoBlocking() throws InterruptedException {
        System.out.println("Initiating HTTP Request:-");

        WebClient webClient = WebClient.create("https://gturnquist-quoters.cfapps.io/api/random");
        Mono<String> result = webClient.get()
                .retrieve()
                .bodyToMono(String.class);
        result.subscribe(WebClientExample::handleResponse);

        System.out.println("Code to run after HTTP Request");

        //wait for a while for the response
        Thread.sleep(1000);
    }

    private static void handleResponse(String s) {
        System.out.println("handle response");
        System.out.println(s);
    }
```

####Blocking vs. Non-Blocking Client
It's a common requirement in web applications to make HTTP calls to other services. Therefore, we need a web client tool.

#####1) RestTemplate Blocking Client
For a long time, Spring has been offering RestTemplate as a web client abstraction. Under the hood, RestTemplate uses the Java Servlet API, which is based on the thread-per-request model.

*This means that the thread will block until the web client receives the response. The problem with the blocking code is due to each thread consuming some amount of memory and CPU cycles.*

Let's consider having a lot of incoming requests, which are waiting for some slow service needed to produce the result.

Sooner or later, the requests waiting for the results will pile up. Consequently, the application will create many threads, which will exhaust the thread pool or occupy all the available memory. We can also experience performance degradation because of the frequent CPU context (thread) switching.

#####2) WebClient Non-Blocking Client
On the other side, WebClient uses an asynchronous, non-blocking solution provided by the Spring Reactive framework.

While RestTemplate uses the caller thread for each event (HTTP call), WebClient will create something like a “task” for each event. Behind the scenes, the Reactive framework will queue those “tasks” and execute them only when the appropriate response is available.

The Reactive framework uses an event-driven architecture. It provides means to compose asynchronous logic through the Reactive Streams API. As a result, the reactive approach can process more logic while using fewer threads and system resources, compared to the synchronous/blocking method.

WebClient is part of the Spring WebFlux library. Therefore, we can additionally write client code using a functional, fluent API with reactive types (Mono and Flux) as a declarative composition.

See here for more information & examples:-
https://www.baeldung.com/spring-webclient-resttemplate

##Spring 5 WebClient 
### Creating a WebClient Instance
There are three options to choose from. The first one is creating a WebClient object with default settings:
```
WebClient client1 = WebClient.create();
```

The second alternative allows initiating a WebClient instance with a given base URI:
```
WebClient client2 = WebClient.create("http://localhost:8080");
```
The last way (and the most advanced one) is building a client by using the DefaultWebClientBuilder class, which allows full customization:
```
WebClient client3 = WebClient
  .builder()
    .baseUrl("http://localhost:8080")
    .defaultCookie("cookieKey", "cookieValue")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
  .build();
```

###Creating a WebClient Instance with Timeouts
Oftentimes, the default HTTP timeouts of 30 seconds are too slow for our needs. Let's see how to configure them for our WebClient instance.

The core class we use is TcpClient.

There, we can set the connection timeout via the ChannelOption.CONNECT_TIMEOUT_MILLIS value. And, we can set the read and write timeouts using a ReadTimeoutHandler and a WriteTimeoutHandler, respectively:
```
TcpClient tcpClient = TcpClient
  .create()
  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
  .doOnConnected(connection -> {
      connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
      connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
  });
 
WebClient client = WebClient.builder()
  .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
  .build();
```




















##  Basics of Web Application with Spring MVC

Let's play and learn more about Spring MVC

### Steps 1 to 7 - Build a normal Web Application
- Understand Basics of HTTP 
- HttpRequest - GET/POST, Request Parameters
- HTTP Response - Response Status - 404,200,500 etc
- Introduction to JSP, Servlets,  Scriptlets and EL
- HTML Form -  Method, Action & Form Data
- Understand Basics of using Maven, Tomcat and Eclipse
- Using Request Attributes for passing Model between Servlet and View

### Steps 11 to 17 : Basics of Spring MVC
- Step 11 : Configure application to use Spring MVC
- Step 12 : First Spring MVC Controller, @ResponseBody, @Controller
- Step 13 : Redirect to Login JSP - LoginController, @ResponseBody and View Resolver
- Step 14 : DispatcherServlet and Log4j
- Step 15 : Show userid and password on the welcome page - ModelMap and @RequestParam 
- Step 16 : LoginService and Remove all JEE Servlets based code
- Step 17 : Spring Auto-wiring and Dependency Management - @Autowired and @Service

## Complete Code Example

### /pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.in28minutes</groupId>
	<artifactId>in28Minutes-springmvc</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>

	<dependencies>
		<dependency>
			<groupId>javax</groupId>
			<artifactId>javaee-web-api</artifactId>
			<version>6.0</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>4.2.2.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>
	</dependencies>

	<build>
		<pluginManagement>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.2</version>
					<configuration>
						<verbose>true</verbose>
						<source>1.8</source>
						<target>1.8</target>
						<showWarnings>true</showWarnings>
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.apache.tomcat.maven</groupId>
					<artifactId>tomcat7-maven-plugin</artifactId>
					<version>2.2</version>
					<configuration>
						<path>/</path>
						<contextReloadable>true</contextReloadable>
					</configuration>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
</project>
```
### /src/main/java/com/in28minutes/login/LoginController.java
```
package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam String name,
			@RequestParam String password) {

		if (!loginService.validateUser(name, password)) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}

		model.put("name", name);
		return "welcome";
	}
}
```
### /src/main/java/com/in28minutes/login/LoginService.java
```
package com.in28minutes.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
	}

}
```
### /src/main/resources/log4j.properties
```
log4j.rootLogger=TRACE, Appender1, Appender2
 
log4j.appender.Appender1=org.apache.log4j.ConsoleAppender
log4j.appender.Appender1.layout=org.apache.log4j.PatternLayout
log4j.appender.Appender1.layout.ConversionPattern=%-7p %d [%t] %c %x - %m%n
 
```
### /src/main/webapp/WEB-INF/todo-servlet.xml
```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.in28minutes" />

    <mvc:annotation-driven />
    
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix">
            <value>/WEB-INF/views/</value>
        </property>
        <property name="suffix">
            <value>.jsp</value>
        </property>
    </bean>
    
</beans>
```
### /src/main/webapp/WEB-INF/views/login.jsp
```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/login" method="POST">
        Name : <input name="name" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
    </form>
</body>
</html>
```
### /src/main/webapp/WEB-INF/views/welcome.jsp
```
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
Welcome ${name}. You are now authenticated.
</body>
</html>
```
### /src/main/webapp/WEB-INF/web.xml
```
<web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
    version="3.0">

    <display-name>To do List</display-name>

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/todo-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

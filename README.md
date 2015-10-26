This sample project shows an incompatibility between Spring LDAP 2.0.4.RELEASE
and Spring Data Rest 2.4.0.RELEASE (Gosling).

To reproduce the error simple clone the project and invoke:

`mvn spring-boot:run`

The application will fail at startup with

`org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.data.rest.core.mapping.ResourceMappings]: Factory method 'resourceMappings' threw exception; nested exception is java.lang.IllegalArgumentException: PersistentEntity must not be null!`

When starting with

`mvn -Pfowler spring-boot:run`

accessing

http://localhost:8080/users

Will fail with the suppressed exception:

```
java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:221)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:110)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:776)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:705)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:959)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:893)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:967)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:858)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:622)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:843)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:291)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:239)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:77)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:239)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:85)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:239)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:219)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:106)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:502)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:142)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:88)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:518)
	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1091)
	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:673)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1526)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.run(NioEndpoint.java:1482)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:745)
Caused by: java.lang.IllegalArgumentException: PersistentEntity must not be null!
	at org.springframework.util.Assert.notNull(Assert.java:112)
	at org.springframework.data.rest.webmvc.PersistentEntityResource$Builder.<init>(PersistentEntityResource.java:155)
	at org.springframework.data.rest.webmvc.PersistentEntityResource$Builder.<init>(PersistentEntityResource.java:137)
	at org.springframework.data.rest.webmvc.PersistentEntityResource.build(PersistentEntityResource.java:129)
	at org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler.wrap(PersistentEntityResourceAssembler.java:105)
	at org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler.toResource(PersistentEntityResourceAssembler.java:84)
	at org.springframework.data.rest.webmvc.AbstractRepositoryRestController.entitiesToResources(AbstractRepositoryRestController.java:121)
	at org.springframework.data.rest.webmvc.AbstractRepositoryRestController.toResources(AbstractRepositoryRestController.java:83)
	at org.springframework.data.rest.webmvc.RepositoryEntityController.getCollectionResource(RepositoryEntityController.java:202)
	... 45 more
```

The actual error is expressed as:

```
2015-10-26 11:37:28.769 DEBUG 76895 --- [nio-8080-exec-3] .m.m.a.ExceptionHandlerExceptionResolver : Resolving exception from handler [public org.springframework.hateoas.Resources<?> org.springframework.data.rest.webmvc.RepositoryEntityController.getCollectionResource(org.springframework.data.rest.webmvc.RootResourceInformation,org.springframework.data.rest.webmvc.support.DefaultedPageable,org.springframework.data.domain.Sort,org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler) throws org.springframework.data.rest.webmvc.ResourceNotFoundException,org.springframework.web.HttpRequestMethodNotSupportedException]: java.lang.IllegalArgumentException: PersistentEntity must not be null!
2015-10-26 11:37:28.770 DEBUG 76895 --- [nio-8080-exec-3] o.s.b.f.s.DefaultListableBeanFactory     : Returning cached instance of singleton bean 'repositoryRestExceptionHandler'
2015-10-26 11:37:28.771 DEBUG 76895 --- [nio-8080-exec-3] .m.m.a.ExceptionHandlerExceptionResolver : Invoking @ExceptionHandler method: org.springframework.http.ResponseEntity<org.springframework.data.rest.webmvc.support.ExceptionMessage> org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler.handleMiscFailures(java.lang.Exception)
```

Accessing

http://localhost:8080/working/users

```
$ http localhost:8080/working/foos
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Date: Mon, 26 Oct 2015 16:53:10 GMT
Server: Apache-Coyote/1.1
Transfer-Encoding: chunked

[
    {
        "id": "cn=John Doe,ou=Development,ou=IT,ou=Departments"
    },
    {
        "id": "cn=Some Dude,ou=Development,ou=IT,ou=Departments"
    },
    {
        "id": "cn=John Smith,ou=Support,ou=IT,ou=Departments"
    },
    {
        "id": "cn=Mordac Preventor of IS,ou=Information Services,ou=IT,ou=Departments"
    },
    {
        "id": "cn=System,ou=System,ou=IT,ou=Departments"
    },
    {
        "id": "cn=Jane Doe,ou=General,ou=Accounting,ou=Departments"
    }
]
```

succeeds since it uses Spring MVC rather than Spring Data (which tries to provide Hypermedia)
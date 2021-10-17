"# Cassava-Project" 

### Bug/01
*  [CP-1] non-static variable LOGGER cannot be referenced from a static context


### FIX/01
*  Made LOGGER Variable static


### Bug/02
*  [ERROR] /intelligent-network-api/src/main/java/com/econetwireless/in/soap/service/
*  IntelligentNetworkService.java:[7,17] package javax.jws does not exist


### FIX/02
*  Added “jakarta.xml.ws” as a maven dependency and reload the pom file (intelligent-network-api)


### Bug/03
*  Could not run IntelligentNetworkPublisher because of the following error

 - Provider com.sun.xml.internal.ws.spi.ProviderImpl was not found


### FIX/03
*  Added 'com.sun.xml.ws' dependency in the Intelligent network api module


### Bug/04
*  Could not build the project because it could not find annotation @PreInsert 


### FIX/04
*  Changed @PreInsert to PrePersist in SubscriberRequest class


### Bug/05
*  Error javax.annotation does not exist


### FIX/05
*  Added the dependency javax.annotation


### Bug/06
*  Expected dot operator after super keyword in PartnerCodeValidatorImpl class


### FIX/06
*  Removed 'this(super)' since this takes in argument of RequestPartnerDao instead of super class


### Bug/07
*  Failed to execute goal org.jvnet.jax-ws-commons:jaxws-maven-plugin:2.3:wsimport


### FIX/07
*  Added a dependency 'org.codehaus.mojo' and same plugin


### Bug/08
*  Package javax.xml.ws does not exist

### FIX/08
*  Added the missing dependency


### Bug/09
*  Persist and update methods in do not exist


### FIX/09
*  Changed persist and update to save in EnquiriesServiceImpl and CreditsServiceImpl


### Bug/10
*  Error in named query: SubscriberRequest.findByPartnerCode org.hibernate.hql.internal.ast.QuerySyntaxException: 
 - Request is not mapped [select r from Request r where r.partnerCode = :partnerCode order by r.dateCreated desc ]


### FIX/10
*  Changed 'Request' in named query in Subscriber class to request to match the name given in @entity


### Bug/11
*  2021-10-14 11:12:21 INFO RequestInterceptor:67 --> IN Enquire Airtime Balance :: Partner Code : null, Mobile Number : 774222278
*  2021-10-14 11:12:21 ERROR RequestInterceptor:77 --> Custom Error on airtime balance :
*  com.econetwireless.utils.execeptions.EpayException: Invalid partner code supplied : null


### FIX/11
*  Autowired EpayRequestProcessor and ReportingProcessor in EpayResource since these were not initialised and produced java.lang.NullPointerException
*  Changed 'and' to '&&' on @around annotations in RequestInterceptor this has no effect on code compilation
*  Added '@PathVariable(partnerCode)' on the enquireAirtimeBalance method parameter in Epay Resource


### Bug/12
*  com.sun.xml.ws.fault.ServerSOAPFaultException: Client received SOAP Fault from server: com/econetwireless/utils/enums/ResponseCode
*  Please see the server log to find more detail regarding exact cause of the failure.


### FIX/12
*  Added ‘this.code’ in ResponseCode constructor to refer to the instance variable


### FIX **NB Computer switched off abruptly had saved the error on notepad and was lost and so forgot what the error was all about and had cleared the log file
*  Changed airtimeTopupRequest.getPartnerCode() to airtimeTopupRequest.getMsisdn() RequestInterceptor to get correct log


### Added @WebParam in enquireBalance method for partnerCode in IntelligentNetworkService interface 


### TESTS
*  Manually Created Test Cases for MessageConverters Class and generated others using an AI plugin called diffblue
*  This achieved class coverage of 52% for classes, 47% for methods and 37% for lines


### JETTY
Ran jetty but there was port binding and so I Created jetty.xml with changes made to port number to avoid port binding with intelligent network publisher and referenced the file under jetty configuration in pom file
and then 
I removed jetty.xml used to change port number and the line in pom file to reference the xml file, because I had realised there was no need to change port number because the cause of port binding was being caused by another program on my machine using that same port number 8080 being used by jetty and so I stopped the program inorder to start jetty on port 8080 and it ran successfully


### CONNECTION POOL
Enabled connection pool for the intelligent-network-api module Web Service by creating
a class called com.econetwireless.epay.dao.config.EcoDataSourceFactory in electronic-payments-dao module which is used to create that database and set connection pool properties etc...
The class implements DataSourceFactory and Connection properties. I also added the following dependency
<dependency>
           <groupId>com.mchange</groupId>
           <artifactId>c3p0</artifactId>
          <version>0.9.5.5</version>
</dependency>
Which I used to create an instance of ComboPooledDataSource
I commented out code in com.econetwireless.epay.dao.config.DataSourceConfiguration inside the method 'datasource', I then made the class a subclass of EmbeddedDatabaseBuilder
so I can have access to setDataSourceFactory method which takes in an argument of DataSourceFactory and returns a Datasource after calling the build method.
I passed in an instance of the EcoDataSourceFactory class in the method

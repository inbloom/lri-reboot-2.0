#LRI Reboot 2.0

##DESCRIPTION
LRI Reboot 2.0 is a new implementation of the Learning Resource Index backed by an RDBMS and coded in Java.

##SYSTEM REQUIREMENTS
LRI Reboot 2.0 has been tested on Ubuntu 12.04 LTS and Ubuntu 13.04 using Oracle Java 7 and Tomcat 7, with Apache Solr 4.4.0 for search. To build the software, you will need Maven 3. You will also need to build and install [spring-jsonp-support](https://github.com/bhagyas/spring-jsonp-support "spring-jsonp-support") in your local maven repository.

##INSTALLATION
To run on a single Ubuntu 12.04 machine using Jetty and H2 with Solr running on localhost:8983, simply download the ZIP archive, unzip it, change to the unzipped folder, and:

	mvn jetty:run

##CONTRIBUTE

##LICENSING
LRI Reboot 2.0 is licensed under the Apache License, Version 2.0. See LICENSE.txt for full license text.

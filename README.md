WildFly Hibernate-Spatial (PostGIS) example
===========================================

Example of testing Hibernate Spatial with PostGIS using Arquillian and embedded WildFly AS.

Used components:
* WildFly 8.1.0.Final
* Hibernate 4.3.5.Final (provided by WildFly)
* Hibernate Spatial 4.3
* Arquillian 1.1.5.Final

Databases that were tested to be compatible:
* PostgreSQL 9.3 with PostGIS 2.1 extension

## How to use?

Just download, create file `etc/datasource.properties` with connection information of your PostGIS database according to `etc/datasource.sample.properties` and then `mvn test`.

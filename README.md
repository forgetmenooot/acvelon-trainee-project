How to run:

1)
- startup tomcat and mysql
- create schema from dump.sql
- change port of mysql location, user and password if it is needed in path WEB-INF/classes/connection.properties (unzip war, change, archieve in war)
- deploy war to tomcat (either from manager or move war to webapps directory of tomcat)
- localhost:8080/film-project

2)
- startup tomcat and mysql
- create schema from dump.sql
- open project and configure maven
- change port of mysql location, user and password if it is needed in path src/main/resources/connection.properties
- run maven command "package",  war file must be created in target folder of the project
- deploy war to tomcat (either from manager or move war to webapps directory of tomcat)
- localhost:8080/film-project



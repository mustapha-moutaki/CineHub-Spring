#FROM tomcat:10.1.46
#FROM tomcat:9.0
## Remove default apps
#RUN rm -rf /usr/local/tomcat/webapps/*
#
## Copy WAR file
##COPY target/CineHub-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
#
#EXPOSE 8080
#CMD ["catalina.sh", "run"]


FROM tomcat:10.1.46
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/CineHub-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
# subscriber-assignment
An Assignment Project With Restfull Services and In-Memory Cache
The assignment is implemented through TDD concepts. The unit and integration tests can be viewed under /src/main/test/
The project consist of mainly model, service and controller packages. There are also configuration , 
components, exception and logging packages.
#Caching
For in memory cache Hazelcast is used. Implementation and configuration of Hazelcast can be viewed under configuration and service packages
#Logging
For logging the request actions Spring AOP is used the pointcuts of the project controller are signed listened through each request is triggered
the logs are written into /logs/app.log file 
The configuration of logging is done through logback.xml 
#Exception
The exception issues are handled through custom SubscriberNotFoundException class  which is used for rest services
the implemantation of exceptions can be viewed under exception package.
#Scheduling
The cache content is written into data.json file in every minutes
the scheduling is done through Spring EnableScheduling annotation. 
In the FileProcess class the writetoFile method is marked with @Scheduled(cron = "${data.update.scheduler}")
the cron value can be configured from application.properties file 
#Tests
The requests can be found under postman_collections folder for testing the api\
Also the data.json is already placed under data/ folder. 

## Attention 
If you want test another data json file or under another folder please configure the applications.properties file
under /src/main/resources
data.file.path can be assigned to different file path 



# Build 

./mvnw clean package


Asynchronous RESTFul web service example

Steps to execute the code :
1> Build and package the project using below mentioned maven command

mvn clean compile package

You will get the corresponding jar "AsyncRestWebService-0.0.1-SNAPSHOT.jar" in the target folder

2> Go to the jar location and run the executable jar with the below command to start the service

java -jar AsyncRestWebService-0.0.1-SNAPSHOT.jar

This will start application with the tomcat embedded in the spring boot

3> Open the browser and call the below API to see the output rendered in the browser in chunks

http://localhost:8080/chunks/string


Output:

Each
words
in
this
sentence
...
...
...
operation
taking
long
time  
## Overview  
This is a Maven project implementing the Purchase and Integration interfaces provided in the ./lib/pluginapi.jar  

## Requirement  
Maven needs to be insyalled to build the project.

## Project build  
 + Navigate to directory where pom.xml is.  
 + From command line run:  
```
mvn install
```

## Testing  
Junit tests are provided and run when project is built.  
To run only the tests run:
```
mvn verify
```

Integration tests provided in pluginapi.jar will run after unit tests complete successfully.  

## Source code  

### Interface implementations  
 + FunFlowersPurchase implements Purchase  
 + FunFlowersIntegration implements Integration  

### Other classes  
 + ApiConsumer  
 Class used to connect to the web API ["http://sandbox.flexionmobile.com/javachallenge/rest/developer/"]("http://sandbox.flexionmobile.com/javachallenge/rest/developer/")

### Additional information  
Additional information on classes and methods can be found in the source code comments and Javadoc

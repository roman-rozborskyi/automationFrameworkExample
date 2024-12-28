# javatpoint automation

[The author's profile on Linkedin](https://www.linkedin.com/in/roman-rozborskyi/)\
Module-Based Testing Framework. Created to show an approach to developing automated tests.\
The framework is divided into three parts:

- uitests
- apitests
- services

This approach allows for low coupling between all three parts, makes the code more readable, and eliminates the
possibility of creating excessive relations between classes.\
The UI part of the framework is divided into four layers:
- tests
- steps
- page objects
- page elements

To run test suite from command line must be executed command:\
```mvn clean test -P ui -DsuiteFileName=smokeUi```\
Where "ui" is the type of tests. The value "ui" or "api" is allowed. The "suiteFileName" parameter is the name of the
test suite without the .xml extension. Both parameters depend on each other. An error will be thrown if the UI test
suite is run with the "api" option.\
For reports creation ExtentReports and AspectJ are used 

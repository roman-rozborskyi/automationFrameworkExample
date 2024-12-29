# javatpoint automation

[The author's profile on Linkedin](https://www.linkedin.com/in/roman-rozborskyi/)\
Framework serves as a foundational template for developing a commercial-grade testing framework tailored to real-world
projects.
Its modular design and layered architecture enable easy customization and scalability, making it adaptable to various
project requirements and testing scenarios.
This framework demonstrates an approach to developing automated tests.
While it is functional, it is still in the development stage, with plans to add additional functionality in the future.

### Architectural Structure

The framework is composed of independent modules:

- uitests
- apitests
- services

This modular approach ensures low coupling between components, making the code more readable and reducing the risk of
excessive interdependencies between classes.
Modules can easily be replaced or extended, allowing new modules to be integrated into the project.\
The UI part of the framework is organized into four distinct layers, each designed to invoke methods only from the
subsequent layer.
This structure ensures clean code development and prevents the mixing of abstraction levels.\
\
UI layers:

1. Tests. Invoke methods only from the steps layer
2. Steps: Define a domain-specific language (DSL) for clear and understandable test development.
   Methods from the page objects layer are invoked here, and actions are logged into the report.
3. Page Objects: Encapsulate the logic for interacting with specific pages.
   They either manipulate specific page elements or call methods from the page elements layer.
4. Page Elements: Encapsulate common elements across all pages, such as inputs, dropdowns, tables, and menus, etc...

### Running the Test Suite

To execute the test suite from the command line, use the following command::\
```mvn clean test -P ui -DsuiteFileName=smokeUi```\
Here:

- "ui" refers to the type of tests, with options "ui" or "api" allowed.
- "suiteFileName" specifies the name of the test suite (without the .xml extension).

Both parameters are interdependent; for instance, an error will occur if the UI test suite is run with the "api" option.

### Reporting

Reports are generated using ExtentReports and AspectJ, ensuring detailed insights into the test execution process.

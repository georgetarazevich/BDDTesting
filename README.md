# BDDTesting
BDD tests to see possibility


Quick Start
-----------
### Pre-requisites

In order to run tests you'll need:

* Java 11+, which can be downloaded [from Oracle](https://www.oracle.com/java/technologies/downloads/)
* [Maven v3+](https://maven.apache.org/install.html)
* The Chrome browser [built by Google](https://www.google.com/chrome/)
* 4 Gb RAM on VM or PC with OS (Windows 10 or higher, Linux Ubuntu 20.04 or higher)  where tests will be run
* Screen resolution at least 1024x768

### Running tests:
1. To run tests - check out the project to a local directory
2. Run tests using Maven: `mvn clean install`

### Checking  results
When the tests are finished, the folder 'target' contains:
1. tests_log.log file with information on each test
2. surefire-reports subfolder with emailable-report
3. allure-results subfolder to see results in Allure report

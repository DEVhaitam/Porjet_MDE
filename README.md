# Porjet_MDE
# Topic:
  Model-Driven Deployment and Configuration:	- CI/CD
=> **Goal :** Create a model-driven system that automates the deployment and configuration of applications
![image](https://github.com/DEVhaitam/Porjet_MDE/assets/87667785/8fcf2eca-f60c-4ba3-81cb-9259a2b13115)

## Abstract Class - CI/CD Phase:
  - Blueprint for other classes
  - Generic attribute: **name**
  - Action to be executed: **execute**
## Build Class:
  - Attributes: **sourceCodeLocation, buildTool, and buildScript**
  - Action: **build()**
## Test Class:
  - Attributes: **testCases, testFramework, and testResults**
  - Action: **runTests()**
## Deploy Class:
  - Attributes: **deploymentScripts, targetEnvironment, and deploymentStrategy**
  - Action: **deploy()**
## CI/CD Pipeline Class:
  - The entire CI/CD pipeline
  - Contains references to the already mentioned classes


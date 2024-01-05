# Porjet_MDE
# Topic:

# Introduction:
Welcome to our CI/CD Pipeline Generator project! This tool automates the generation of Continuous Integration and Continuous Deployment (CI/CD) pipelines for backend applications built with Maven. The aim is to streamline the process of setting up robust and efficient CI/CD workflows tailored to Maven-based projects.
# Problem Statement:
The deployment and management of backend microservices applications, particularly those built using Maven, often pose challenges. Developers encounter significant hurdles when attempting to manually configure Continuous Integration and Continuous Deployment (CI/CD) pipelines for these applications. The complexity of integrating various tools, managing dependencies, and ensuring consistent deployment practices can lead to inefficiencies, errors, and delays in the software development lifecycle.
# Proposed Solution:
Automating the process of turning input, which includes data jobs and events for the CI/CD Pipeline, into a CI/CD pipeline using GitHub Actions and a model transformation language can make development quicker and easier. This helps speed up the setup of deployment pipelines for developers, making the process faster and more reliable.
# Solution Architecture:
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/architecture.png)

# Input Meta-model: 
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/DiagramInput.png)

# Mainfile Meta-model:
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/Output.png)

# Transformations

## M2M Transformation

### 1. Rule: InputJob2PipelineJob

This rule transforms individual jobs (`Source!Job`) from the source model to corresponding jobs (`Target!Job`) in the target model. It sets up basic job information such as name and execution environment (`runsOn`). Additionally, it handles the transformation of environment variables and pipeline steps based on the job type ("build," "test," or "deploy").

- **Inputs:**
  - `s`: Source!Job - Input job from the source model
- **Outputs:**
  - `t`: Target!Job - Transformed job in the target model

#### Transformation Details

- Basic job information such as name and runsOn is mapped from source to target.
- Environment variables are transformed from source to target.
- Pipeline steps are added based on the type of job ("build," "test," or "deploy").

### 2. Rule: Input2Pipeline

This rule transforms the entire pipeline configuration (`Source!Input`) to the main file in the target model (`Target!MainFile`). It includes the transformation of individual jobs and the definition of events and branches.

- **Inputs:**
  - `s`: Source!Input - Input pipeline configuration from the source model
- **Outputs:**
  - `t`: Target!MainFile - Transformed main file in the target model

#### Transformation Details

- Pipeline name is mapped from source to target.
- Individual jobs are transformed using the `InputJob2PipelineJob` rule.
- Events and branches are defined in the target model based on the source model.



# Porjet_MDE
# Topic:

# Introduction:
Welcome to our CI/CD Pipeline Generator project! This tool automates the generation of Continuous Integration and Continuous Deployment (CI/CD) pipelines for backend applications built with Maven. The aim is to streamline the process of setting up robust and efficient CI/CD workflows tailored to Maven-based projects.
# Problem Statement:
The deployment and management of backend microservices applications, particularly those built using Maven, often pose challenges. Developers encounter significant hurdles when attempting to manually configure Continuous Integration and Continuous Deployment (CI/CD) pipelines for these applications. The complexity of integrating various tools, managing dependencies, and ensuring consistent deployment practices can lead to inefficiencies, errors, and delays in the software development lifecycle.
# Proposed Solution:
Automating the process of turning input, which includes data jobs and events for the CI/CD Pipeline, into a CI/CD pipeline using GitHub Actions and a model transformation language can make development quicker and easier. This helps speed up the setup of deployment pipelines for developers, making the process faster and more reliable.
# Architecture de la solution:
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/architecture.png)

# Meta-model input : 
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/DiagramInput.png)

# Meta-model Mainfile:
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/Output.png)
# Transformation:
In the context of model-driven engineering, model transformation aims
to to specify how to produce target models from a set of source models. This
allows developers to define how the elements of the source model should be used
to initialize the elements of the target model. In our project, we used two types
of model transformation :

— Model to Model Transformation
— Model to Text Transformation
This table resumes the Transformation from Input Metal-model to Mainfile Meta-model and focuses on key elements of both meta-models.
![image](https://github.com/DEVhaitam/Porjet_MDE/blob/main/Imgs/trans.png)



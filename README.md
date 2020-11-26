# test-assignment
**Description**
This repository contains solution to the test task.  
https://drive.google.com/file/d/1jf_hHrNRw-1vAE9eaNuAMt0Jov9CkAJG/view?usp=sharing  
For the above index.html file, achieve the below tasks.
Tasks:
1. Correct all of the deficiencies in index.html  

2. "Sectors" selectbox:  
2.1. Add all the entries from the "Sectors" selectbox to database  
2.2. Compose the "Sectors" selectbox using data from database  

3. Perform the following activities after the "Save" button has been pressed:   
3.1. Validate all input data (all fields are mandatory)  
3.2. Store all input data to database (Name, Sectors, Agree to terms)  
3.3. Refill the form using stored data   
3.4. Allow the user to edit his/her own data during the session  

---

## Technology Stack Used
1. JAVA 8
2. Spring Boot
3. Hibernate
4. Maven (pom.xml) is present for dependency management.
5. Dependencies such as Spring Web, Thymeleaf, Spring Data JPA, Spring Security, H2 Database, Junit test, Spring Boot Validation etc.

---

## Artifacts
Please go through this section to find more about the key artifacts used in the solution

1. Authentication and Session Management files included inside **src/main/java/com/test/assignment/auth/**
2. Controller File is included inside **src/main/java/com/test/assignment/user/controller/**
3. Service File is included inside **src/main/java/com/test/assignment/user/application/service/**
4. Model and Repository files included inside **src/main/java/com/test/assignment/user/domain/**
5. Project **pom.xml** file is present at root folder
6. Test files are located inside **src/test/java/com/test/assignment**
7. Index.html file path is at **src/main/resources/templates/**
8. The main method that runs the Spring Boot application is at **src/main/java/com/test/assignment/AssignmentApplication.java**

---

## Steps to execute the project
STEP 1: Clone the Spring Boot project from Github on local machine.  
STEP 2: Import the solution into IntelliJ as maven project.  
STEP 3: Select **AssignmentApplication**, right click and select Run **AssignmentApplication**.  
STEP 4: Access the application at "http://localhost:8080/".  
STEP 5: Login using "guest/guest" to access the form index.html.  


---

# Pre-requisites
1. Java 8 should be installed.

---

## Notes
Please keep in mind the following:

1. This application is protected by Spring Security to maintain authentication(Using String password instead of char array for simplicity).
2. Initial User data and hierarchical Sector data is populated using code inside **AssignmentApplication** which creates User and Sector entities and save it calling save method on repository.
3. Login using "test/test" or " "guest/guest". More users can be created using similar code inside **AssignmentApplication**.
4. Using Thymeleaf as tempate engine to render view along with html.
5. **index.html** file includes **src/main/resources/templates/categoryFragment.html** to display the hierarchical worksectors recursively.
6.  Controller consists of 2 methods to fetch the user data and save the user data.

---

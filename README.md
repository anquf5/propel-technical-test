# Background

This project is for the technical test, build an address book web application, with all data stored in a JSON flat file. The application be able to list, view, add, edit and delete records. This project use JAVA as programming language.


# Environment

JDK 19
SpringBoot 3.0

# File Structure
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───propel
│   │   │           └───technicaltest
│   │   └───resources
│   └───test
│       └───java
│           └───com
│               └───propel
│                   └───technicaltest

# API Documentation
 
### /list
Description: List all the users
Method: GET
Param: 
Return:   
 - List

### /view/{id}
Description: View an user
Method: GET
Param:

 - id - Integer

Return: 
 - JsonObject

### /add
Description: Add a new user
Method: POST
Param:
Body:

 - firstName - String
 - lastName - String
 - phone - String
 - email - String

Return: 
 - JsonObject

### /{id}
Description: Edit a user
Method: PUT
Param: 

 - id - Integer

Body:

 - firstName - String
 - lastName - String
 - phone - String
 - email - String

Return: 
 - JsonObject

### /{id}
Description: Delete a user
Method: DELETE
Param:

 - id - Integer

Return: 
 - JsonObject

# I. Goals over the Guideline

## A. Technical Goals
1. Maven
2. SpringBoot
3. Java 17
4. H2 Database
5. JWT
6. Swagger (Open API)
7. Unit Testing (Postman collection)
8. GitHub public repository
9. Readme.md

## B. Functional Goals
1. Accept and return JSON only
2. Outgoing messages should consider a status section as suggested in the guide
3. Use incoming structure defined on evaluation guide
4. Use UUID type for key fields
5. Return http status code depending the result
6. For the success cases to return control fields
7. Validate email existence and return error message
8. Email format validation
9. Password custom validation (on property password.expression.regexp)

# II. Configure and Start

## A. Configuration
1. Modify application.properties
2. Compile application
3. Execute main class using IDE: cl.bci.exercise.Launcher
4. Execute application running: java -jar exercise-pkg.jar

## B. Resources
1. [Postman collection](https://www.postman.com/beehappychile/workspace/bci/collection/7222690-1172c1fe-7edb-4728-9219-d0e5f5f32b7f?action=share&creator=7222690) 
2. [Swagger portal](http://localhost:8080/swagger)
3. [Open API declaration](http://localhost:8080/api)
4. [H2 console](http://localhost:8080/h2) admin@admin

# III. Execute test

## A. Obtain Token
* Goal: obtain a valid JWT Token
* Consider token secret key (spring.security.secret)
* Consider token vigency in seconds (spring.security.duration)
* Valid user: reinaldo.horie@bci.cl with 'hola' like password 
* Obtain the token from header response section: Authorization Bearer

## B. Create User
* Goal: create a user and phone structure according guideline specification
* Put the rescued token into Authorization section and select Bearer Token
* Consider a valid email
* Consider a strong password according to the regular expression defined (password.expression.regexp)

## C. Obtain User
* Goal: obtain a registered user using email like key
* Put the rescued token into Authorization section and select Bearer Token
* Consider a registered email (step B.)

## D. Disable User
* Goal: change isValid value alternating about last status
* Put the rescued token into Authorization section and select Bearer Token
* Consider a registered email (step B.)
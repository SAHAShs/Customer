# Customer CRUD 
## Prerequisites
### Backend
- Download and import project
- Create Database **CUSTOMER** and **LOGIN**
- Table creation will be handled by Spring Boot.
- add datasource properties to **application.properties** `src/main/resources`
- Default **userName** and **password** to access application is mentioned, change that if you want to.
- Run the main class package`com.customer`

### Fontend
- index.html ----> Login page
- home.html ----> Home page
- edit.html ----> Edit customer data
- register.html ----> add customers
## Usage
> Open index.html with liver server and provide username and password mentioned in **application.properties**

>JWT token will generate and redirects to home page,if not you cant do any operations.

>Add customer will redirects to register.html page where you can add customer.

>Sync button will fetch data from remote api, remote api url is specifies in **application.properties** change it, for now api is generated in this application only.

>Logout functionality will remove token from cookie which is stored when Log In

>Search functionality gives **searchTerm, searchCriteria, Sorting order, Number records per page**, from drop down you can select criteria, next input term and next drop down for recods per page.

> According to total number of pages page numbers will be generated click on them to switch between pages.

>To sort click on table head for now **id, first name, lastname** allowed

# Endpoints
## Access with Postman 
Go to postman workspace
##
### `POST http://localhost:8080/login`
This url is for login which will give you a token in response.
copy that token
##
### Make sure to add JWT Token generated during login in ***Authorization -> Bearer token*** here after.
##
### `POST http://localhost:8080/createCustomer`
provide data in body 
##
### `PUT http://localhost:8080/updateCustomer/id`

Provide data in body and id in url
##

### `DELETE http://localhost:8080/deleteCustomer/id`

Give id of customer to delete customer
##
### `GET http://your-api-base-url/search?searchCriteria=<value>&searchTerm=<value>&pageNumber=<value>&records=<value>&sortField=<value>&sortOrder=<value>`

Replace value with the appropriate values for your request. Here's a breakdown of the parameters:

-   `searchCriteria`: The criteria for searching customers.
-   `searchTerm`: The term to search for.
-   `pageNumber`: The page number for pagination (default is 0).
-   `records`: The number of records per page (default is 10).
-   `sortField`: The field to use for sorting (default is "id").
-   `sortOrder`: The sort order (default is "ASC").
##
### `http://localhost:8080/new`
you can create new user for application, add credentials in body to generate new user

# Structure
Customer/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── customer/
│   │   │   │   │   ├── restcontroller/
│   │   │   │   │   │   ├── AuthenticationController.java
│   │   │   │   │   │   └── LoginController.java
│   │   │   │   │   │   └── DummyDataController.java
│   │   │   │   │   │   └── LoginController.java
│   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── LoginService.java
│   │   │   │   │   │   └── CustomerService.java
│   │   │   │   │   │   ├── JwtService.java
│   │   │   │   │   │   └── UserInfoUserDetailsService.java
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   ├── UserRepository.java
│   │   │   │   │   │   └── CustomerRepository.java
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── CustomerDao.java
│   │   │   │   │   │   └── LoginDao.java
│   │   │   │   │   ├── config/
│   │   │   │   │   │   ├── UserInfo.java
│   │   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   │   │   └── CrosConfig.java
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── Admin.java
│   │   │   │   │   │   └── Customer.java
│   │   │   │   │   └── CustomerApplication.java
│   │   └── resources/
│   │       └── application.properties
├── test/
│   ├── java/
│   └── resources/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── README.md
└── pom.xml


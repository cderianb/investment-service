# Investment Service

## Description
This is an application built using Java 21, PostgreSQL, and reactive programming with R2DBC to manage investment-related activities. 
It provides API endpoints for creating and managing master data entities such as investors, borrowers, and tranches. Users can invest in tranches, and initiate monthly repayments.
Another various investment-related operations will be added in future updates.


## Tech Stack Overview
- Java 21
- Postgres SQL
- Reactive Programming
- R2DBC

## Key Features
- <b>Master Data Management</b>: The application allows the creation and management of master data entities such as investors, borrowers, and tranches. Users can easily add, retrieve, update, and delete these entities via dedicated API endpoints.

- <b>Investment Transactions</b>: Users can invest in or buy tranches through the provided API endpoints. The application supports  querying investment details and managing investment portfolios.

- <b>Monthly Repayments</b>: A key feature of the application is its ability to handle monthly repayment processes. Users can initiate and track repayment transactions, with the application ensuring accurate and timely processing of repayments.
 
## Getting Started:

This section will guide you through the installation and setup process to get the application up and running in your environment.
<br>To install and set up the Investment Service Web API Application, follow these steps:

### 1. Prerequisites:
- Java 21
- PostgreSQL

### 2. Configure the Database:

- Open the application's configuration file `/investment-service/src/main/resources/application.properties`.
- Update the database connection settings to match your PostgreSQL database instance.
  - spring.r2dbc.url=r2dbc:postgresql://\<server>:\<port>/\<database_name>
  - spring.r2dbc.username=\<username>
  - spring.r2dbc.password=\<password>
- Run the database migrations to initialize required tables at `/investment-service/src/main/resources/migrations/Initialize.sql`
  
### 3. Use the application:
To use the application or APIs, navigate to `/docs/apis/` to look at the api specification or navigate to `/docs/apis/postman-collection `and use postman to import the api collection
# InvestorController.md

## Create Investor
- url: http://localhost:8080/investor
- method: POST
- validations:
  - username must not blank
  - username must unique
  - username must not contains whitespace
  - name must not blank
- query param:
- request:
    ```json lines
  {
        "username":"string",
        "name":"string",
        "riskProfile":"string",
        "sourceOfFund": "string"
    }
    ```
- response:
- ```json lines
  {
        "statusCode": 200,
        "result": true,
        "data": {
            "id": 1,
            "username": "string",
            "name": "string",
            "riskProfile": "string",
            "sourceOfFund": "string"
        },
        "errors": null,
        "pagination": null
    }
  ```

## Get Investor List
- api: http://localhost:8080/investor
- method: GET
- validations:
- query param:
  - page
  - pageSize
  - sortBy
  - sortOrder
  - username
  - name
- request:
- response:
- ```json lines
    {
        "statusCode": 200,
        "result": true,
        "data": [
            {
                "id": 1,
                "username": "string",
                "name": "string",
                "riskProfile": "string",
                "sourceOfFund": "string"
            }
        ],
        "errors": null,
        "pagination": {
            "currentPage": 1,
            "totalPage": 3,
            "pageSize": 2,
            "totalData": 5
        }
    }
    ```

## Get Investor
- api: http://localhost:8080/investor/{id}
- method: GET
- validations:
  - Investor id must exist
- query param:
- request:
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 1,
        "username": "string",
        "name": "string",
        "riskProfile": "string",
        "sourceOfFund": "string"
    },
    "errors": null,
    "pagination": null
  }
  ```

## Update Investor
- api: http://localhost:8080/investor/{id}
- method: PUT
- validation:
  - Investor id must exist
- query param:
- request:
- ```json
    {
      "name": "string",
      "riskProfile": "string",
      "sourceOfFund": "string"
    }
    ```
- response:
- ```json lines
    {
      "statusCode": 200,
      "result": true,
      "data": {
        "id": 1,
        "username": "string",
        "name": "string",
        "riskProfile": "string",
        "sourceOfFund": "string"
      },
      "errors": null,
      "pagination": null
    }
    ```

## Delete Investor
- api: http://localhost:8080/investor/{id}
- method: DELETE
- validations:
  - Investor id must exist
  - Investor must not invest
- query param:
- request:
- response:
- ```json lines
    {
      "statusCode": 200,
      "result": true,
      "data": 1,
      "errors": null,
      "pagination": null
    }
  ```

## Get Investor Overview
- api: http://localhost:8080/investor/{id}/overview
- method: GET
- validations:
  - Investor id must exist
- query param:
- request:
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "totalInvestment": 34,
        "totalActiveInvestment": 30,
        "totalCompleteInvestment": 4,
        "totalInvestedAmount": 924044735,
        "totalEarnedInterest": 195803.916666666652,
        "totalCompanyInvested": 2,
        "investmentList": [
            {
                "borrowerId": 8,
                "companyName": "Burger King",
                "amountInvested": 100000000,
                "monthlyInterest": 3.75E+7,
                "status": "ACTIVE",
                "startDate": 1702918800000,
                "maturityDate": 1766077200000,
                "endDate": null
            }
        ]
    },
    "errors": null,
    "pagination": null
  }
  ```

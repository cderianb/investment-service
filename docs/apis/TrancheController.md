# TrancheController.md

## Create Tranche
- url: http://localhost:8080/tranche
- method: POST
- validations:
    - name must not blank
    - borrower id must exist
- query param:
- request:
    ```json lines
  {
      "name":"Whooper 4",
      "borrowerId":8,
      "annualInterestRate":4.5,
      "amountAvailableForInvestment":1,
      "duration":24,
      "maximumInvestmentAmount":1000000000,
      "minimumInvestmentAmountPertranche":100000,
      "maximumInvestmentAmountPertranche":1000000000
  }
    ```
- response:
- ```json lines
  {
      "statusCode": 200,
      "result": true,
      "data": {
        "id": 22,
        "name": "string",
        "borrowerId": 8,
        "annualInterestRate": 4.5,
        "amountAvailableForInvestment": 1000000000,
        "duration": 24,
        "maximumInvestmentAmount": 1000000000,
        "minimumInvestmentAmountPertranche": 100000,
        "maximumInvestmentAmountPertranche": 1000000000,
        "borrower": null
      },
      "errors": null,
      "pagination": null
    }
  ```

## Get Tranche List
- api: http://localhost:8080/tranche
- method: GET
- validations:
- query param:
    - page
    - pageSize
    - sortBy
    - sortOrder
    - name
    - company
- request:
- response:
- ```json lines
    {
      "statusCode": 200,
      "result": true,
      "data": [
          {
              "id": 22,
              "name": "string",
              "borrowerId": 8,
              "annualInterestRate": 4.5,
              "amountAvailableForInvestment": 1000000000,
              "duration": 24,
              "maximumInvestmentAmount": 1000000000,
              "minimumInvestmentAmountPerInvestor": 100000,
              "maximumInvestmentAmountPerInvestor": 1000000000,
              "borrower": {
                  "id": 8,
                  "companyName": "string",
                  "picName": "string",
                  "phone": "string",
                  "business": "string",
                  "address": "string"
              }
          }
      ],
      "errors": null,
      "pagination": {
          "currentPage": 1,
          "totalPage": 1,
          "pageSize": 20,
          "totalData": 4
      }
    }
    ```

## Get tranche
- api: http://localhost:8080/tranche/{id}
- method: GET
- validations:
    - tranche id must exist
- query param:
- request:
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 21,
        "name": "string",
        "borrowerId": 8,
        "annualInterestRate": 4.5,
        "amountAvailableForInvestment": 78770763,
        "duration": 24,
        "maximumInvestmentAmount": 1000000000,
        "minimumInvestmentAmountPerInvestor": 100000,
        "maximumInvestmentAmountPerInvestor": 1000000000,
        "borrower": {
            "id": 8,
            "companyName": "string",
            "picName": "string",
            "phone": "string",
            "business": "string",
            "address": "string"
        }
    },
    "errors": null,
    "pagination": null
  }
  ```

## Update tranche
- api: http://localhost:8080/tranche/{id}
- method: PUT
- validation:
    - tranche id must exist
    - tranche must not been invested
    - name must not blank
    - updated name must unique
- query param:
- request:
- ```json
  {
    "name": "string",
    "annualInterestRate": 0.02,
    "duration": 24,
    "maximumInvestmentAmount": 1000000,
    "minimumInvestmentAmountPerInvestor": 100000,
    "maximumInvestmentAmountPerInvestor": 500000
  }
    ```
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 19,
        "name": "string",
        "borrowerId": 8,
        "annualInterestRate": 0.02,
        "amountAvailableForInvestment": 1000000,
        "duration": 24,
        "maximumInvestmentAmount": 1000000,
        "minimumInvestmentAmountPerInvestor": 100000,
        "maximumInvestmentAmountPerInvestor": 500000,
        "borrower": null
    },
    "errors": null,
    "pagination": null
  }
    ```

## Delete tranche
- api: http://localhost:8080/tranche/{id}
- method: DELETE
- validations:
    - tranche id must exist
    - tranche must not been invested
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

## Get tranche Overview
- api: http://localhost:8080/tranche/{id}/overview
- method: GET
- validations:
    - tranche id must exist
- query param:
- request:
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "totalInvestors": 2,
        "activeInvestments": 1,
        "completeInvestments": 2,
        "totalInvestedAmount": 601514,
        "totalReceivedAmount": 589483.72,
        "investors": [
            {
                "investorId": 6,
                "investorName": "string",
                "amountInvested": 200000,
                "platformStatus": "ACTIVE"
            }
        ]
    },
    "errors": null,
    "pagination": null
  }
  ```

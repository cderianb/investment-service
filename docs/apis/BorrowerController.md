# BorrowerController.md

## Create Borrower
- url: http://localhost:8080/borrower
- method: POST
- validations:
- query param:
- request:
    ```json lines
  {
      "companyName":"string",
      "picName":"string",
      "phone":"string",
      "business":"string",
      "address":"string"
  }
    ```
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 10,
        "companyName": "string",
        "picName": "string",
        "phone": "string",
        "business": "string",
        "address": "string"
    },
    "errors": null,
    "pagination": null
  }
  ```

## Get Borrower List
- api: http://localhost:8080/borrower
- method: GET
- validations:
- query param:
    - page
    - pageSize
    - sortBy
    - sortOrder
    - companyName
    - picName
    - business
- request:
- response:
- ```json lines
    {
    "statusCode": 200,
    "result": true,
    "data": [
        {
            "id": 10,
            "companyName": "string",
            "picName": "string",
            "phone": "string",
            "business": "string",
            "address": "string"
        }
    ],
    "errors": null,
    "pagination": {
        "currentPage": 1,
        "totalPage": 1,
        "pageSize": 20,
        "totalData": 8
    }
  }
    ```

## Get Borrower
- api: http://localhost:8080/borrower/{id}
- method: GET
- validations:
    - Borrower id must exist
- query param:
- request:
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 8,
        "companyName": "string",
        "picName": "string",
        "phone": "string",
        "business": "string",
        "address": "string"
    },
    "errors": null,
    "pagination": null
  }
  ```

## Update Borrower
- api: http://localhost:8080/borrower/{id}
- method: PUT
- validation:
    - Borrower id must exist
- query param:
- request:
- ```json
    {
      "companyName": "string",
      "picName": "string",
      "phone": "string",
      "business": "string",
      "address": "string"
    }
    ```
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 8,
        "companyName": "string",
        "picName": "string",
        "phone": "string",
        "business": "string",
        "address": "string"
    },
    "errors": null,
    "pagination": null
  }
    ```

## Delete Borrower
- api: http://localhost:8080/borrower/{id}
- method: DELETE
- validations:
    - Borrower id must exist
    - Borrower must not have tranche
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
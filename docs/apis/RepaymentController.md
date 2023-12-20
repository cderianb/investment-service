# RepaymentController.md

## Monthly Repayment
- url: http://localhost:8080/platform/_invest
- method: POST
- validations:
- query param:
- request:
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": [
        {
            "id": 52,
            "platformId": 54,
            "repaymentDate": 1703005200000,
            "period": 1,
            "interestPaid": 833.3333333333334,
            "platformFee": 10000.0
        }
    ],
    "errors": null,
    "pagination": null
  }
  ```
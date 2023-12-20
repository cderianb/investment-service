# PlatformController.md

## Invest
- url: http://localhost:8080/platform/_invest
- method: POST
- validations:
    - Tranche maximum investment not reached
    - Amount invested must in tranche's investment range
    - Maximum amount invested per customer not reach
    - Investor id must exist
    - Tranche id must exist
- query param:
- request:
    ```json lines
  {
      "investorId":5,
      "trancheId":21,
      "amountInvested":100000000
  }
    ```
- response:
- ```json lines
  {
    "statusCode": 200,
    "result": true,
    "data": {
        "id": 54,
        "investorId": 6,
        "trancheId": 19,
        "status": "ACTIVE",
        "startDate": 1703005200000,
        "maturityDate": 1766163600000,
        "endDate": null,
        "monthlyInterest": 833.333333333333333,
        "amountInvested": 500000,
        "platformFee": 10000.00,
        "borrowerReceive": 490000.00,
        "investor": null,
        "tranche": null
    },
    "errors": null,
    "pagination": null
  }
  ```
# How to use Guide

The basic usage of this service is thru an REST API

## REST API

### Customer API

Customer API manages customers

Actions:

    - Create customer
    - Delete customer
    - List customers

Base URL:

```
http://localhost:8080/transfer-service/api/customers/
```

### Accounts API

Accounts API depends on Customer ID for executing actions over accounts

Actions:

    - Create an account 
    - Delete an account
    - List customer's accounts
    - Make a transfer
    
Base URL:

```
http://localhost:8080/transfer-service/api/customers/{customerId}/accounts
```

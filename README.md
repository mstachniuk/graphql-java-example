# graphql-java-example

## Build and run

```
./gradlew clean build
./gradlew bootRun

```

go to: http://localhost:8000/graphiql

First request

```
{
  customer(id: "2") {
    id
    name
    email
    company {
      id
      name
      website
    }
    orders {
      id
      status
      items {
        id
        name
        amount
        price
        currency
        producer {
          id
        }
      }
    }
  }
}
```

or without graphiql:

```
curl -X POST -H "Content-Type: application/json" -d '{
    "query":"{\n  customer(id: \"2\") {\n    id\n    name\n    email\n    company {\n      id\n      name\n      website\n    }\n    orders {\n      id\n      status\n      items {\n        id\n        name\n        amount\n        price\n        currency\n        producer {\n          id\n        }\n      }\n    }\n  }\n}",
    "variables":null,
    "operationName":null
}' "http://localhost:8000/graphql"
```

Response

```
{
  "data": {
    "customer": {
      "id": "2",
      "name": "name",
      "email": "a@b.com",
      "company": {
        "id": "211",
        "name": "Company Corp.",
        "website": "www.company.com"
      },
      "orders": [
        {
          "id": "55",
          "status": "NEW",
          "items": [
            {
              "id": "101",
              "name": "Rubber duck",
              "amount": 2,
              "price": "5",
              "currency": "USD",
              "producer": {
                "id": "12"
              }
            },
            {
              "id": "102",
              "name": "Magic Ball",
              "amount": 1,
              "price": "10",
              "currency": "USD",
              "producer": {
                "id": "13"
              }
            }
          ]
        },
        {
          "id": "66",
          "status": "DONE",
          "items": [
            {
              "id": "103",
              "name": "Super Bike",
              "amount": 1,
              "price": "1000",
              "currency": "USD",
              "producer": {
                "id": "14"
              }
            }
          ]
        }
      ]
    }
  }
}
```

Get all customers

Request

```
{
  customers {
    id
    name
    email
  }
}
```

Response

```
{
  "data": {
    "customers": [
      {
        "id": "2",
        "name": "name",
        "email": "a@b.com"
      },
      {
        "id": "19",
        "name": "MyName",
        "email": "me@my.com"
      }
    ]
  }
}
```

## Variables


Request:

```
query queryName($customerID: String) {
  customers(id: $customerID) {
    id
    name
    email
  }
}
```

Query Variables:

```
{
  "customerID": "2"
}
```

Response

```
{
  "data": {
    "customers": {
      "id": "2",
      "name": "name",
      "email": "a@b.com"
    }
  }
}
```

## Create

Create Customer Request

```
mutation {
  createCustomer(input: {name: "MyName" email: "me@me.com" clientMutationId: "123"}) {
    customer {
      id
      name
      email
    }
    clientMutationId
  }
}
```

Response

```
{
  "data": {
    "createCustomer": {
      "customer": {
        "id": "570",
        "name": "MyName",
        "email": "me@me.com"
      },
      "clientMutationId": "123"
    }
  }
}
```
# graphql-java-example

## Build and run

```
./gradlew clean build
./gradlew bootRun

```

go to: http://localhost:8000/graphiql

## Get Customer by id 

Request 

```graphql
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

```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "query":"{\n  customer(id: \"2\") {\n    id\n    name\n    email\n    company {\n      id\n      name\n      website\n    }\n    orders {\n      id\n      status\n      items {\n        id\n        name\n        amount\n        price\n        currency\n        producer {\n          id\n        }\n      }\n    }\n  }\n}",
    "variables":null,
    "operationName":null
}' "http://localhost:8000/graphql"
```

Response

```json
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

Now try yourself to remove some lines from request.

## Get all Customers

Request

```graphql
{
  customers {
    id
    name
    email
  }
}
```

Response

```json
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

You can pass some variables to your query to avoid string concatenation. E.g.:

```graphql
query queryName($customerID: String!) {
  customer(id: $customerID) {
    id
    name
    email
  }
}
```

Query Variables:

```json
{
  "customerID": "2"
}
```

Response

```json
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

## Interfaces

Your query cen return interface. 
Interface can have a lot implementations.
E.g. interface `User` is implemented by `Admin` and `Moderator` and both have more fields then interface definition.

```graphql
{
  users {
    __typename
    id
    name
    email
    ... on Admin {
      superAdmin
    }
    ... on Moderator {
      permissions
    }
  }
}

```

Response

```json
{
  "data": {
    "users": [
      {
        "__typename": "Admin",
        "id": "777",
        "name": "Admin a",
        "email": "admin@me.com",
        "superAdmin": true
      },
      {
        "__typename": "Moderator",
        "id": "888",
        "name": "Moderator",
        "email": "mod@me.com",
        "permissions": [
          "Delete Customer",
          "Delete Comment"
        ]
      }
    ]
  }
}

```

## Union

Your query can also return Union. It means type1 or type2 or type3...
Types don't requre a common interface.

```graphql
{
  search(input: "a") {
    __typename
    ... on User {
      id
      name
    }
    ... on Admin {
      superAdmin
    }
    ... on Moderator {
      permissions
    }
    ... on User {
      id
      name
    }
    ... on Customer {
      id
      name
      company {
        name
      }
    }
  }
}
```

Response

```json
{
  "data": {
    "search": [
      {
        "__typename": "Customer",
        "id": "2",
        "name": "name",
        "company": {
          "name": "Company Corp."
        }
      },
      {
        "__typename": "Admin",
        "id": "777",
        "name": "Admin a",
        "superAdmin": true
      },
      {
        "__typename": "Moderator",
        "id": "888",
        "name": "Moderator",
        "permissions": [
          "Delete Customer",
          "Delete Comment"
        ]
      }
    ]
  }
}

``` 

## Create Customer

Create Customer Request

```graphql
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

```json
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

## Create Customers 1

Create Customers Request using createCustomer mutation. 
This solution use previous mutation + aliases.

```graphql
mutation {
  cust1: createCustomer(input: {
    name: "a"
    email: "a@a.a"
    clientMutationId: "123"
  }) {
    customer {
      id
      name
      email
    }
    clientMutationId
  }
  cust2: createCustomer(input: {
    name: "b"
    email: "b@b.b"
    clientMutationId: "123"
  }) {
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

```json
{
  "data": {
    "cust1": {
      "customer": {
        "id": "807",
        "name": "a",
        "email": "a@a.a"
      },
      "clientMutationId": "123"
    },
    "cust2": {
      "customer": {
        "id": "824",
        "name": "b",
        "email": "b@b.b"
      },
      "clientMutationId": "123"
    }
  }
}
```

## Create Customers 2

Create Customers Request using array in input - another mutation

```graphql
mutation mut1($cust: [CreateCustomer]){
  createCustomers(input: {
    customers: $cust, 
    clientMutationId: "123"}) {
    customers {
      id
      name
      email
    }
    clientMutationId
  }
}
```

Query Variables:

```json
{
  "cust": [
    {
      "name": "c",
      "email": "c@c.c"
    },
    {
      "name": "d",
      "email": "d@d.d"
    }
  ]
}
```

Response

```json
{
  "data": {
    "createCustomers": {
      "customers": [
        {
          "id": "97",
          "name": "c",
          "email": "c@c.c"
        },
        {
          "id": "531",
          "name": "d",
          "email": "d@d.c"
        }
      ],
      "clientMutationId": "123"
    }
  }
}
```

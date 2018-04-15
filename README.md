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
  customers(id: "22") {
    id
    name
    email
    company {
      id
      name
    }
  }
}
```

or without graphiql:

```
curl -X POST -H "Content-Type: application/json" -d '{
    "query":"{customers(id: \"22\") { id name email}}",
    "variables":null,
    "operationName":null
}' "http://localhost:8000/graphql"
```

Response

```
{
  "data": {
    "customers": {
      "id": "22",
      "name": "name",
      "email": "a@b.com",
      "company": null
    }
  }
}
```


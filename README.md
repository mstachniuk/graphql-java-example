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
  test
}
```

or without graphiql:

```
curl -X POST -H "Content-Type: application/json" -d '{
        "query":"{test}",
        "variables":null,
        "operationName":null
}' "http://localhost:8000/graphql"
```

Response

```
{
  "data": {
    "test": "response"
  }
}
```


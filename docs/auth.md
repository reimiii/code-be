## Auth API Spec

### Base URL

`http://localhost:8181/api/v1/auth`

## Endpoints

### Register

Request :

- Method : POST
- Endpoint : `/register`
- Header :
    - Content-Type: application/json
    - Accept: application/json

Body :

```json 
{
  "name": "string",
  "username": "unique | string",
  "password": "string"
}
```

Response - 200 OK :

```json 
{
  "token": "random string base64"
}
```

Response - 400 Bad Request :

```json 
{
    "name": "must not be blank",
    "username": "must not be blank",
    "password": "must not be blank"
}
```
Response - 409 Conflict :

```json 
{
    "code": "409 CONFLICT",
    "error": "username already taken"
}
```
---
### Authenticate

Request :

- Method : POST
- Endpoint : `/authenticate`
- Header :
  - Content-Type: application/json
  - Accept: application/json

Body :

```json 
{
  "username": "unique | string",
  "password": "string"
}
```

Response - 200 OK :

```json 
{
  "token": "random string base64"
}
```

Response - 400 Bad Request :

```json 
{
    "username": "must not be blank",
    "password": "must not be blank"
}
```
Response - 404 Not Found :

```json 
{
    "code": "404 NOT_FOUND",
    "error": "username or password wrong"
}
```

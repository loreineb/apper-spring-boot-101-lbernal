# The Blog Service

## Register Blogger

`POST /blogger`
```json
{
  "email": "hdoe@apper.ph",
  "name": "John Doe",
  "password": "jdoe123"
}
```
1. All fields are required.
2. Email format must be valid.
3. Password must be at least 5 characters.
4. Make sure email is not yet registered.

`Response: 201 - Created`
```json
{
  "id": "<UUID>",
  "date_registration": "2023-07-11T23:23:12"
}
```

## Get Blogger

`GET /blogger/{id}`

`Response: 200 - OK`
```json
{
  "id": "<UUID>",
  "name": "John Doe",
  "email": "jdoe@apper.ph",
  "date_registration": "2023-07-11T23:23:12"
}
```
1. If id not found, response with HTTP 404 (response body is up to you)

## Get All Blogger

`GET /blogger`

`Response: 200 - OK`
```json
[
  {
    "id": "<UUID>",
    "name": "John Doe 1",
    "email": "jdoe@apper.ph",
    "date_registration": "2023-07-11T23:23:12"
  },
  {
    "id": "<UUID>",
    "name": "John Doe 2",
    "email": "jdoe2@apper.ph",
    "date_registration": "2023-07-11T23:23:12"
  }
]
```
## Create Blog

`POST /blog`
```json
{
  "title": "This is the title",
  "body": "This is the actual content",
  "blogger_id": "<UUID>"
}
```
`Response: 201 - CREATED`
```json
{
  "id": "<UUID>",
  "blogger_id": "<UUID>",
  "created_at": "2023-07-11T23:23:12",
  "last_updated": "2023-07-11T23:23:12"
}
```

1. All fields are required

## Update Blog

`PUT /blog/{blog_id}`
```json
{
  "title": "This is the updated title",
  "body": "This is the updated content",
}
```
`Response: 204 - NO CONTENT`
```json
{
  "id": "<UUID>",
  "blogger_id": "<UUID>",
  "created_at": "2023-07-11T23:23:12",
  "last_updated": "2023-08-11T23:23:12"   // this must be updated
}
```
1. All fields are required

## Get blog

`GET /blog/{id}`

`Response: 200 - OK`
```json
{
  "blogger_id": "<UUID>",
  "title": "The updated title",
  "body": "The updated content",
  "created_at": "2023-07-11T23:23:12",
  "last_updated": "2023-08-11T23:23:12"
}
```

## Get All blogs

`GET /blog`

`Response: 200 - OK`
```json
[
  {
    "blogger_id": "<UUID>",
    "title": "The updated title",
    "body": "The updated content",
    "created_at": "2023-07-11T23:23:12",
    "last_updated": "2023-08-11T23:23:12"
  },
  {
    "blogger_id": "<UUID>",
    "title": "The updated title2",
    "body": "The updated content2",
    "created_at": "2023-07-11T23:23:12",
    "last_updated": "2023-08-11T23:23:12"
  }
]
```
## Get All blogs by blogger

`GET /blog/blogger/{blogger_id}`

`Response: 200 - OK`
```json
[
  {
    "title": "The updated title",
    "body": "The updated content",
    "created_at": "2023-07-11T23:23:12",
    "last_updated": "2023-08-11T23:23:12"
  },
  {
    "title": "The updated title2",
    "body": "The updated content2",
    "created_at": "2023-07-11T23:23:12",
    "last_updated": "2023-08-11T23:23:12"
  }
]
```
### Submission
Email Subject: [Spring Boot 101 | Capstone] YourName
Includes:
1. Link of the repo
2. Key takeaways/
3. [Optional] Brutal/Super Honest feedback to me :)
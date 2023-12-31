# Applicazioni-Web1-2022-2023
## Course project - Ticket System
M-I5040 C-I5041(P) Applicazioni Web 1  - SUPSI - I3A -  2022/23





# Description
This API service provides a RESTful API to manage a web application that will serve as a ticket tracking platform.
The service allows you to view, create, edit and delete tickets. The various requests will be made by sending the Ticket values in JSON format.



# Get Ticket/s

Used to get a Ticket or all Tickets.

**URL** : `/api/tickets/<id>`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
      "id": 1,
      "title": "Ticket 1",
      "description": "Description of the ticket 1",
      "author": "Pippo"
}
```

## Error Response

**Condition** : If the requested Ticket doesn't exist.

**Code** : `404 NOT FOUND`

**Content** :

```PrettyPrint
Ticket not found
```



# Add Ticket

Used to create a new Ticket.

**URL** : `/api/tickets/`

**Method** : `POST`

**Data constraints**

```json
{
    "title": "[title of the ticket]",
    "description": "[description of the ticket]",
    "author": "[author of the ticket]"
}
```

**Data example**

```json
{
      "title": "Ticket 1",
      "description": "Description of the ticket 1",
      "author": "Pippo"
}
```

## Success Response

**Code** : `201 CREATED`

**Content example**

```json
{
      "id": 1,
      "title": "Ticket 1",
      "description": "Description of the ticket 1",
      "author": "Pippo"
}
```

## Error Response

**Condition** : If the type of the request is not json.

**Code** : `400 Bad Request`

**Content** :

```PrettyPrint
Invalid content type
```



# Modify Ticket

Used to modify an existing Ticket.

**URL** : `/api/tickets/<id>`

**Method** : `PUT`

**Data constraints**

```json
{
    "title": "[title of the ticket]",
    "description": "[description of the ticket]",
    "author": "[author of the ticket]"
}
```

**Data example**

```json
{
      "title": "Ticket 1",
      "description": "Description of the ticket 1",
      "author": "Pippo"
}
```

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
      "id": 1,
      "title": "Ticket 1",
      "description": "Description of the ticket 1",
      "author": "Pippo"
}
```

## Error Response

**Condition** : If the requested Ticket doesn't exist.

**Code** : `404 NOT FOUND`

**Content** :

```PrettyPrint
Ticket not found
```

**Condition** : If the type of the request is not json.

**Code** : `400 Bad Request`

**Content** :

```PrettyPrint
Invalid content type
```



# Delete Ticket

Used to delete an existing Ticket.

**URL** : `/api/tickets/<id>`

**Method** : `DELETE`

## Success Response

**Code** : `204 NO CONTENT`

## Error Response

**Condition** : If the requested Ticket doesn't exist.

**Code** : `404 NOT FOUND`

**Content** :

```PrettyPrint
Ticket not found
```

**Condition** : If the type of the request is not json.

**Code** : `400 Bad Request`

**Content** :

```PrettyPrint
Invalid content type
```



# Patch Ticket

Used to modify a part of an existing Ticket.

**URL** : `/api/tickets/<id>`

**Method** : `PATCH`

**Data constraints** (only the field/s to modify)

```json
{
    "description": "[description of the ticket]"
}
```

**Data example**

```json
{
      "title": "New Ticket"
}
```

## Success Response

**Code** : `200 OK`

**Content example** (the entire Ticket, not only the modified field/s)

```json
{
      "id": 1,
      "title": "New Ticket",
      "description": "Description of the ticket 1",
      "author": "Pippo"
}
```

## Error Response

**Condition** : If the requested Ticket doesn't exist.

**Code** : `404 NOT FOUND`

**Content** :

```PrettyPrint
Ticket not found
```

**Condition** : If the type of the request is not json.

**Code** : `400 Bad Request`

**Content** :

```PrettyPrint
Invalid content type
```

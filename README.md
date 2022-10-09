# Description
This API service provides a RESTful API to manage a web application that will serve as a ticket tracking platform.
The service allows you to view, create, edit and delete tickets. The various requests will be made by sending the Ticket values in JSON format.



# Get Ticket/s

Used to get a Ticket or all Tickets.

**URL** : `/api/tickets/*`

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

**Condition** : If the type of the request is not json.

**Code** : `400 Invalid content type`



# Modify Ticket

Used to modify an existing Ticket.

**URL** : `/api/tickets/*`

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



# Delete Ticket

Used to delete an existing Ticket.

**URL** : `/api/tickets/*`

**Method** : `DELETE`

## Success Response

**Code** : `200 OK`

**Content example**

```PrettyPrint
Ticket deleted
```
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
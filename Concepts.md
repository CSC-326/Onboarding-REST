

Simply put, an API (or Application Programming Interface) defines a specification for how to communicate with a computer program or subset thereof. An API lays out what functionality is offered, what parameters must be provided when making calls, and provides some guarantees about the return type given the preconditions are satisfied. Fundamentally, a complete and well-documented API makes it easier to use a piece of code as a “building block” for building a larger system.

A REST API is a subtype of API that is geared towards Internet-facing systems. The goal of REST APIs is to provide painless communication and interoperability between these Internet-facing applications and systems. REST APIs typically allow (often with some sort of authentication) a manipulation of the various types of entities (recipes, inventory, etc) that a web application revolves around.

The key concept behind REST APIs is that they are inherently stateless, that is, everything that a server needs to know to complete a request or a client needs to know about the results is contained within the request itself. This is contrary to the notion of a server maintaining a session where it stores information about a series of transactions with a particular client, or cookies where a client keeps similar information stored on its end.

REST APIs typically consume and produce JSON-formatted content. A successor to XML, JSON documents are easy for a computer to parse and easy for a human to read.

### Verbs

Typically, REST API operations are carried out over HTTP; consequently most REST API actions are based around HTTP verbs (POST, PUT, DELETE, and so on). The actions typically available are:

| HTTP Verb	| Action             |	Example	       |Result  | 
| --------- | ------------------ | --------------- |------- |
| GET	    | Retrieve record(s) | GET /users/	   | Retrieves all users|
| DELETE	| Delete record(s)	 | DELETE /users/1 | Deletes user with id 1|
| POST	    | Create record	     | POST /users/	   | Creates a new user|
| PUT	    | Update record	     | PUT /users/6	   | Updates user 6|
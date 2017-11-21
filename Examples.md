[Introduction](README.md) | [Concepts](Concepts.md) |  [Example REST Call](Examples.md) | [Walkthrough]() | [Fix a bug]() | [CoffeeMaker]()

# Concepts Practice

Ensure that coffeemaker is running and is visible in `http://localhost:8080/inventory.html`

Let's see how we how a javascript client application can communicate with a server using a REST api call.

### Getting the inventory from inside the browser

Open the developer's tool console and executing this code inside of the console.

```Javascript
fetch("/api/v1/inventory")
    .then(data => data.json())
    .then(result => console.log(result));
```

This constructs as simple `GET` request to `/api/v1/inventory` and retrieves the inventory as a json object. You should be able to see results as follows:

![console get](imgs/console_get.png)

### Send a request to update inventory

We can also construct a request that uses the HTTP `PUT` verb, and sends a request body object to the server.
The following request will increase coffee, milk, sugar, and chocolate inventory count by 1.

```Javascript
// The data we are going to send in our request
data = {
    coffee: 1, milk: 1, sugar: 1, chocolate: 1
}
// Headers describing how the request body is formatted.
headers = new Headers();
headers.append('Content-Type', 'application/json');
// Request information
fetchData = { 
    method: 'PUT', 
    body: JSON.stringify(data),
    headers: headers
}
fetch('/api/v1/inventory', fetchData)
    .then(data => data.json())
    .then(result => console.log(result));
```

Executing this code snippet in the browser will increase the all items in inventory by 1.

![console put](imgs/console_put.png)

### Running from a command line

Using curl to access the REST API from your system.


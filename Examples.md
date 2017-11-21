[Introduction](README.md) | [Concepts](Concepts.md) |  [Example REST Call](Examples.md) | [Walkthrough]() | [Fix a bug]() | [CoffeeMaker]()

Calling a REST api from a client.

### Getting the inventory from inside the browser

```Javascript
fetch("/api/v1/inventory")
    .then(data => data.json())
    .then(result => console.log(result));
```

### Send a request to update inventory

This request will increase coffee, milk, sugar, and chocolate inventory count by 1.

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

### Running from a command line

Using curl to access the REST API from your system.


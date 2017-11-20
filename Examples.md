[Introduction](README.md) | [Concepts](Concepts.md) |  [Example REST Call](Examples.md) | [Walkthrough]() | [Fix a bug]() | [CoffeeMaker]()

Calling a REST api from a client.

### Getting the inventory from inside the browser

```
fetch("/api/v1/inventory")
    .then(data => data.json())
    .then(result => console.log(result));
```

### Add an element.

```
const url = '/api/v1/inventory';
// The data we are going to send in our request
let data = {
    coffee: '13'
}
let fetchData = { 
    method: 'PUT', 
    body: data
}
fetch(url, fetchData)
    .then(data => data.json())
    .then(result => console.log(result));
```


### Running from a command line

Using curl to access the REST API from your system.



Calling a REST api from a client.

### Getting the inventory from inside the browser

```
fetch("/api/v1/inventory")
    .then(data => data.json())
    .then(result => console.log(result));
```


### Add an element.

const url = 'https://randomuser.me/api';
// The data we are going to send in our request
let data = {
    name: 'Sara'
}
// The parameters we are gonna pass to the fetch function
let fetchData = { 
    method: 'POST', 
    body: data,
    headers: new Headers()
}
fetch(url, fetchData)
.then(function() {
    // Handle response you get from the server
});


### Running from a command line

Using curl.


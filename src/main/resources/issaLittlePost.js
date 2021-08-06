// fetch for GET, POST and PUT method.

fetch("http://localhost:8080/movies", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify([{"id":6,"title":"Moonrise Kingdom","year":"2012","director":"Wes Anderson","actors":"Bill Murray","imdbID":"100","poster":"No Poster","genre":"Comedy","plot":"Kids go on an adventure"}])
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});

// fetch for DELETE method. Gets the ID and deletes.

fetch("http://localhost:8080/movies", {
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify(2)
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});

// GET METHOD
fetch("http://localhost:8080/movies", {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow'
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});
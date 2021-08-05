fetch("http://localhost:8080/movies", {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    redirect: 'follow',
    body: JSON.stringify([{"id":2,"title":"Moonrise Kingdom","year":"2012","director":"Wes Anderson","actors":"Bill Murray","imdbID":"100","poster":"No Poster","genre":"Comedy","plot":"Kids go on an adventure"}])
}).then(function(response) {
    return response.json();
}).then(function(data) {
    console.log(data);
});


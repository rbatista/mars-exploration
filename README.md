# Mars Exploration
Programming [challenge](https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c) for Backend Engineer at Elo7

## Build and test the application
```sh
./mvnw clean install
```

## Build the docker image
```
docker build --tag mars-exploration:latest .
```

## Run the app container 
```sh
docker run --rm -p "8080:8080" mars-exploration:latest
```

## Access the API Documentation
http://localhost:8080/swagger-ui.html

# Room Occupancy Application

## Run

#### CMD
`gradlew bootJar`

`java -jar build/libs/Room-Occupancy-Application-1.0-SNAPSHOT.jar`

#### Docker
`docker build -f Dockerfile -t room-occupancy-app:v1 .`

`docker run -p 8081:8081 --name room-occupancy-app room-occupancy-app:v1`

## Usage
Send request for endpoint `/book-rooms`

Example of request:
```
curl -X POST \
  http://localhost:8081/book-rooms \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 63496ddc-a0fd-fe57-5292-84b0b4ea2016' \
  -d '{
	"guests" : [{
		"price": 1
	},
	{
		"price": 1
	},
	{
		"price": 123
	},
	{
		"price": 56
	}],
	"quantityFreePremiumRooms" : 1,
	"quantityFreeEconomyRooms" : 1
}'

```

## Tests
#### CMD
`gradlew test`


## API docs
After run application API docs expose on `/swagger-ui.html`
Example:
`http://localhost:8081/swagger-ui.html`

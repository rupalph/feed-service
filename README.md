# FeedService

Restful Services to retrieve User Feeds (tweets). Feed service will return upto 100 most recent tweets for a logged in user.
Currently uses in memory database using hashmap 

## Usage
1. Call Login-Service, obtain Security token.
2. Call Feed-Service with userid, and above security token.

## Examples
FeedService(HTTP-GET):
http://localhost:8080/FeedService/api/feed/{userid}?authToken=[Random Token]
<pre>
{
  "result": [
      {
        "message": "my new car",
        "userid": "1000",
        "timestamp": "1970-12-31 16:00:00"
      },
      {
        "message": "lets go to park",
        "userid": "1000",
        "timestamp": "1970-12-31 16:00:00"
      },
      {
        "message": "hello",
        "userid": "1000",
        "timestamp": "1970-12-31 16:00:00"
      }
    ] 
}</pre>


Login-Service (To get auth-token):
Http GET: http://localhost:8080/FeedService/api/login/{userid}
<pre>[Random Token]</pre>




##API Configurations
* In memory cache currently configured with size of 100 tweet objects
* Configure in app.properties file in config dir
* For e.g, app.properties:
cache.size=100



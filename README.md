# microservices-challenge

## Running the project

* run the services:
    * docker-compose --compatibility up
* If you want to test that the value that is obtained from the mock service (I called it percentage-service), is down,
  you could run the following command to stop the percentage-service containers:
    * docker stop $(docker ps --filter "name=percentage-service" -q)
* To test the api I added a postman collection with some examples. Feel free to create another examples to test the
  apis.
* I have also added a github actions just to show that all the test passed.
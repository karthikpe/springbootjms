POC Setup:
--------------
- This poc requires ActiveMQ server up and running.
- Default port for the server is 8161.
- Verify the activeMq server using 
	http://127.0.0.1:8161/admin  
- The app listens to the queue "poc.event" which can be seen in activeMq admin console.

POC Testing:
-----------
- Start the spring boot application
- This poc can be tested using a REST client or ActiveMQ.

Event message json test data:
----------------------------
{
  "body": {
    "bookingReference": "FRNQ3HD", 
    "operations": [
      {
        "op": "add", 
        "path": "/body/flights/20121024LISFNC7609/passengers/87654325/comments/897", 
        "value": {
          "commentCode": "897", 
          "commentText": "Voluntary Offload", 
          "eResLocationCode": "LTN", 
          "userFirstName": "John", 
          "userId": 12345, 
          "userSurname": "Smiths"
        }
      }, 
      {
        "op": "replace", 
        "path": "/body/outbounds/flights/20121024LISFNC7609/passengers/87654325/comments/897", 
        "value": {
          "commentCode": "897", 
          "commentText": "Voluntary Offload", 
          "rowStatus": "Deleted", 
          "userId": null
        }
      }, 
      {
        "op": "replace", 
        "path": "/body/flights/20121024LISFNC7609/passengers/87654325/passengerDetails/name/title", 
        "value": "Mr"
      }, 
      {
        "op": "replace", 
        "path": "/body/flights/20121024LISFNC7609/passengers/87654325/passengerDetails/name/firstName", 
        "value": "Joe"
      }, 
      {
        "op": "replace", 
        "path": "/body/flights/error", 
        "value": "Joe"
      }, 
      {
        "op": "replace", 
        "path": "/body/bookingContact/name/lastName", 
        "value": "Smith"
      }, 
      {
        "op": "replace", 
        "path": "/body/bookingContact/address/addressLine2", 
        "value": null
      }
    ]
  }, 
  "header": {
    "messageGenerationTimestamp": "2012-04-23T18:25:43.511Z", 
    "messageName": "bookingUpdated_event", 
    "messageProducerName": "ACP", 
    "messageVersion": "1.0.0", 
    "operationType": "UPDATE"
  }
}

Test using REST Client:
-----------------------
Using curl command as below:

curl -H "Content-Type: application/json" -X POST -d '[event message json test data]' http://localhost:8888/event

Test using ActiveMQ:
-------------------
- Select the queue poc.event
- The message body content as [event message json test data].


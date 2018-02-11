
- This folder has json patch schema files.
- The schema file represents the json patch event file that comes from AL to ACP.

Create schema file:
------------------
- The confluence page has the schema details. Create a new .schema file using the content from confluence page.
	Eg: the file BookingUpdatedAtAirport.schema has been created from 
		http://conf.europe.easyjet.local/display/AL/BookingUpdatedAtAirport+-+Json+Patch+Concrete+Schema

Create DTO's:
------------
- The DTO's are created from schema file and are used for processing the incoming json patch event file.
- Create DTO's using http://www.jsonschema2pojo.org/ and schema.
	- Provide appropriate package name eg: com.poc.event.airport.booking.update.dto
	- Choose Source type: JSON Schema
	- Use the default settings for all other properties
	- Verify using Preview
	- Click Zip to create the DTO's and download the file
- Unzip the generated files to target extension eg: ejqueueintegration


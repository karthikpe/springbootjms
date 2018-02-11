package com.poc.strategy;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Abstract class for the following operations:
 *    - passengerNameChangeNotNullable
 *    - passengerNameChangeNullable
 * 
 * @author  "k.ekambaram"
 *
 */
public abstract class AbstractPassengerDetailsNameStrategy implements EventStrategy {

   String flightKey;

   String paxId;

   String attrName;
   
   String value;


   /**
    * Path regex 1: /body/flights/[A-Z\\d\\s]+/passengers/[^/]+/passengerDetails/name/(title|lastName)
    * Path regex 2: /body/flights/[A-Z\\d\\s]+/passengers/[^/]+/passengerDetails/name/firstName
    * 
    * Value: string or null
    */
   @Override
   public void initialize(String path, JsonNode value) {
      String[] pathValues = path.split("/");
      this.flightKey = pathValues[3];
      this.paxId = pathValues[5];
      this.attrName = pathValues[8];
      this.value = value.toString();
   }

   /**
    * @return the flightKey
    */
   public String getFlightKey() {
      return flightKey;
   }

   /**
    * @return the paxId
    */
   public String getPaxId() {
      return paxId;
   }

   /**
    * @return the attrName
    */
   public String getAttrName() {
      return attrName;
   }

   /**
    * @return the value
    */
   public String getValue() {
      return value;
   }

}

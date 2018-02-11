package com.poc.strategy;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Abstract class for the operation bookingContactNameChanged
 * 
 * @author  "k.ekambaram"
 */
public abstract class AbstractBookingContactNameStrategy implements EventStrategy {

   String attrName;

   String value;

   /**
    * Path regex: /body/bookingContact/name/(firstName|lastName)
    * 
    * Value: string or null
    */
   @Override
   public void initialize(String path, JsonNode value) {
      String[] pathValues = path.split("/");
      this.attrName = pathValues[4];
      this.value = value.toString();
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

package com.poc.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.poc.dto.PassengerCommentDto;
import com.poc.utils.JsonUtils;

/**
 * Abstract class for the following operations:
 *    - passengerCommentsAdd
 *    - passengerCommentsDelete
 * 
 * @author  "k.ekambaram"
 *
 */
public abstract class AbstractPassengerCommentsStrategy implements EventStrategy {

   private static final String VALUE_COMMENT_CODE = "commentCode";
   private static final String VALUE_COMMENT_TEXT = "commentText";
   private static final String VALUE_USER_ID = "userId";
   private static final String VALUE_USER_FIRST_NAME = "userFirstName";
   private static final String VALUE_USER_SUR_NAME = "userSurname";
   private static final String VALUE_ERES_LOCATION_CODE = "eResLocationCode";
   private static final String VALUE_ROW_STATUS = "rowStatus";
   
   String flightKey;

   String paxId;

   String attrName;
   
   PassengerCommentDto value;

   /**
    * Path regex: /body/flights/[A-Z\\d\\s]+/passengers/[^/]+/comments/E?[\\d]+
    * 
    * Value: json [commentCode, commentText, userId, userFirstName, userSurname, eResLocationCode, rowStatus]
    */
   @Override
   public void initialize(String path, JsonNode value) {
      String[] pathValues = path.split("/");
      flightKey = pathValues[3];
      paxId = pathValues[5];
      attrName = pathValues[7];
      
      PassengerCommentDto passengerCommentDto = new PassengerCommentDto();
      passengerCommentDto.setCommentCode(JsonUtils.getValue(value, VALUE_COMMENT_CODE));
      passengerCommentDto.setCommentText(JsonUtils.getValue(value, VALUE_COMMENT_TEXT));
      passengerCommentDto.setUserId(JsonUtils.getValue(value, VALUE_USER_ID));
      passengerCommentDto.setUserFirstName(JsonUtils.getValue(value, VALUE_USER_FIRST_NAME));
      passengerCommentDto.setUserSurname(JsonUtils.getValue(value, VALUE_USER_SUR_NAME));
      passengerCommentDto.seteResLocationCode(JsonUtils.getValue(value, VALUE_ERES_LOCATION_CODE));
      passengerCommentDto.setRowStatus(JsonUtils.getValue(value, VALUE_ROW_STATUS));

      this.value = passengerCommentDto;
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
   public PassengerCommentDto getValue() {
      return value;
   }

}

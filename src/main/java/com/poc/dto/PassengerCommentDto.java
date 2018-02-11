package com.poc.dto;

/**
 * @author karthikekambaram
 *
 */
public class PassengerCommentDto {

   String commentCode;
   
   String commentText;
   
   String userId;
   
   String userFirstName;
   
   String userSurname;
   
   String eResLocationCode;
   
   String rowStatus;

   /**
    * @return the commentCode
    */
   public String getCommentCode() {
      return commentCode;
   }

   /**
    * @param commentCode the commentCode to set
    */
   public void setCommentCode(String commentCode) {
      this.commentCode = commentCode;
   }

   /**
    * @return the commentText
    */
   public String getCommentText() {
      return commentText;
   }

   /**
    * @param commentText the commentText to set
    */
   public void setCommentText(String commentText) {
      this.commentText = commentText;
   }

   /**
    * @return the userId
    */
   public String getUserId() {
      return userId;
   }

   /**
    * @param userId the userId to set
    */
   public void setUserId(String userId) {
      this.userId = userId;
   }

   /**
    * @return the userFirstName
    */
   public String getUserFirstName() {
      return userFirstName;
   }

   /**
    * @param userFirstName the userFirstName to set
    */
   public void setUserFirstName(String userFirstName) {
      this.userFirstName = userFirstName;
   }

   /**
    * @return the userSurname
    */
   public String getUserSurname() {
      return userSurname;
   }

   /**
    * @param userSurname the userSurname to set
    */
   public void setUserSurname(String userSurname) {
      this.userSurname = userSurname;
   }

   /**
    * @return the eResLocationCode
    */
   public String geteResLocationCode() {
      return eResLocationCode;
   }

   /**
    * @param eResLocationCode the eResLocationCode to set
    */
   public void seteResLocationCode(String eResLocationCode) {
      this.eResLocationCode = eResLocationCode;
   }

   /**
    * @return the rowStatus
    */
   public String getRowStatus() {
      return rowStatus;
   }

   /**
    * @param rowStatus the rowStatus to set
    */
   public void setRowStatus(String rowStatus) {
      this.rowStatus = rowStatus;
   }
   
   
}

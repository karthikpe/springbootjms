
package com.poc.event.airport.booking.update.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * See http://conf.europe.easyjet.local/display/EI/Message+Headers
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "messageName",
    "messageVersion",
    "messageGenerationTimestamp",
    "messageProducerName",
    "operationType"
})
public class Header {

    /**
     * Message Name
     * (Required)
     * 
     */
    @JsonProperty("messageName")
    @JsonPropertyDescription("Message Name")
    private String messageName;
    /**
     * Message Version
     * (Required)
     * 
     */
    @JsonProperty("messageVersion")
    @JsonPropertyDescription("Message Version")
    private String messageVersion;
    /**
     * Generated Timestamp, ex. 2016-12-13T12:12:12Z 
     * (Required)
     * 
     */
    @JsonProperty("messageGenerationTimestamp")
    @JsonPropertyDescription("Generated Timestamp, ex. 2016-12-13T12:12:12Z ")
    private String messageGenerationTimestamp;
    /**
     * Producer Name
     * (Required)
     * 
     */
    @JsonProperty("messageProducerName")
    @JsonPropertyDescription("Producer Name")
    private String messageProducerName;
    /**
     * Operation Type. Possible values are CREATE/UPDATE/DELETE
     * (Required)
     * 
     */
    @JsonProperty("operationType")
    @JsonPropertyDescription("Operation Type. Possible values are CREATE/UPDATE/DELETE")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Message Name
     * (Required)
     * 
     */
    @JsonProperty("messageName")
    public String getMessageName() {
        return messageName;
    }

    /**
     * Message Name
     * (Required)
     * 
     */
    @JsonProperty("messageName")
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    /**
     * Message Version
     * (Required)
     * 
     */
    @JsonProperty("messageVersion")
    public String getMessageVersion() {
        return messageVersion;
    }

    /**
     * Message Version
     * (Required)
     * 
     */
    @JsonProperty("messageVersion")
    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }

    /**
     * Generated Timestamp, ex. 2016-12-13T12:12:12Z 
     * (Required)
     * 
     */
    @JsonProperty("messageGenerationTimestamp")
    public String getMessageGenerationTimestamp() {
        return messageGenerationTimestamp;
    }

    /**
     * Generated Timestamp, ex. 2016-12-13T12:12:12Z 
     * (Required)
     * 
     */
    @JsonProperty("messageGenerationTimestamp")
    public void setMessageGenerationTimestamp(String messageGenerationTimestamp) {
        this.messageGenerationTimestamp = messageGenerationTimestamp;
    }

    /**
     * Producer Name
     * (Required)
     * 
     */
    @JsonProperty("messageProducerName")
    public String getMessageProducerName() {
        return messageProducerName;
    }

    /**
     * Producer Name
     * (Required)
     * 
     */
    @JsonProperty("messageProducerName")
    public void setMessageProducerName(String messageProducerName) {
        this.messageProducerName = messageProducerName;
    }

    /**
     * Operation Type. Possible values are CREATE/UPDATE/DELETE
     * (Required)
     * 
     */
    @JsonProperty("operationType")
    public String getOperationType() {
        return operationType;
    }

    /**
     * Operation Type. Possible values are CREATE/UPDATE/DELETE
     * (Required)
     * 
     */
    @JsonProperty("operationType")
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
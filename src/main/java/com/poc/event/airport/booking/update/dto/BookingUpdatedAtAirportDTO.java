
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
 * easyJet JSON schema for updates to a booking. Inspired by JSONPatch http://jsonpatch.com/ <http://jsonpatch.com/> 
 * <p>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "header",
    "body"
})
public class BookingUpdatedAtAirportDTO {

    /**
     * See http://conf.europe.easyjet.local/display/EI/Message+Headers
     * (Required)
     * 
     */
    @JsonProperty("header")
    @JsonPropertyDescription("See http://conf.europe.easyjet.local/display/EI/Message+Headers")
    private Header header;
    /**
     * The root of the booking
     * (Required)
     * 
     */
    @JsonProperty("body")
    @JsonPropertyDescription("The root of the booking")
    private Body body;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * See http://conf.europe.easyjet.local/display/EI/Message+Headers
     * (Required)
     * 
     */
    @JsonProperty("header")
    public Header getHeader() {
        return header;
    }

    /**
     * See http://conf.europe.easyjet.local/display/EI/Message+Headers
     * (Required)
     * 
     */
    @JsonProperty("header")
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * The root of the booking
     * (Required)
     * 
     */
    @JsonProperty("body")
    public Body getBody() {
        return body;
    }

    /**
     * The root of the booking
     * (Required)
     * 
     */
    @JsonProperty("body")
    public void setBody(Body body) {
        this.body = body;
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

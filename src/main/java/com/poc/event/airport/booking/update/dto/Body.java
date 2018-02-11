
package com.poc.event.airport.booking.update.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * The root of the booking
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bookingReference",
    "operations"
})
public class Body {

    /**
     * Reference for the booking obtained from eRes
     * (Required)
     * 
     */
    @JsonProperty("bookingReference")
    @JsonPropertyDescription("Reference for the booking obtained from eRes")
    private String bookingReference;
    /**
     * The list of operations describing the changes to the booking. Utilizes a model inspired by JSON Patch.
     * (Required)
     * 
     */
    @JsonProperty("operations")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("The list of operations describing the changes to the booking. Utilizes a model inspired by JSON Patch.")
    private Set<Object> operations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Reference for the booking obtained from eRes
     * (Required)
     * 
     */
    @JsonProperty("bookingReference")
    public String getBookingReference() {
        return bookingReference;
    }

    /**
     * Reference for the booking obtained from eRes
     * (Required)
     * 
     */
    @JsonProperty("bookingReference")
    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    /**
     * The list of operations describing the changes to the booking. Utilizes a model inspired by JSON Patch.
     * (Required)
     * 
     */
    @JsonProperty("operations")
    public Set<Object> getOperations() {
        return operations;
    }

    /**
     * The list of operations describing the changes to the booking. Utilizes a model inspired by JSON Patch.
     * (Required)
     * 
     */
    @JsonProperty("operations")
    public void setOperations(Set<Object> operations) {
        this.operations = operations;
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
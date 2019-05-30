package com.undefinedlabs.scope.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CarLocation {

    private final String uuid;
    private final double latitude;
    private final double longitude;

    @JsonCreator
    public CarLocation(
            @JsonProperty("uuid") final String uuid,
            @JsonProperty("lat") final double latitude,
            @JsonProperty("lon") double longitude) {
        this.uuid = uuid;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @JsonGetter("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("lat")
    public double getLatitude() {
        return latitude;
    }

    @JsonGetter("lon")
    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CarLocation that = (CarLocation) o;

        return new EqualsBuilder()
                .append(latitude, that.latitude)
                .append(longitude, that.longitude)
                .append(uuid, that.uuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(latitude)
                .append(longitude)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString();
    }
}

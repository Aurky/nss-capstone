package com.nashss.se.bulletinboardservice.models;

import com.nashss.se.bulletinboardservice.dynamodb.models.Venue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class LocationModel {
    private final String locationId;
    private final String name;
    private final List<Venue> venues;

    private LocationModel(String locationId, String name, List<Venue> venues) {
        this.locationId = locationId;
        this.name = name;
        this.venues = venues;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocationModel that = (LocationModel) o;

        return  Objects.equals(locationId, that.locationId) && Objects.equals(name, that.name) &&
                Objects.equals(venues, that.venues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, name, venues);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String locationId;
        private String name;
        private List<Venue> venues;

        public Builder withLocationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withVenues(List<Venue> venues) {
            if (null == venues) {
                this.venues = null;
            } else {
                this.venues = new ArrayList<>(venues);
            }
            return this;
        }

        public LocationModel build() {
            return new LocationModel(locationId, name, venues);
        }
    }
}

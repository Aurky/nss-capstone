package com.nashss.se.bulletinboardservice.models;

import java.util.Objects;

public class VenueModel {
    private final String venueId;
    private final String name;
    private final String description;
    private final String location;

    private VenueModel(String venueId, String name, String description, String location) {
        this.venueId = venueId;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public String getVenueId() {
        return venueId;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VenueModel that = (VenueModel) o;

        return  Objects.equals(venueId, that.venueId) && Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueId, name, description, location);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String venueId;
        private String name;
        private String description;
        private String location;


        public Builder withVenueId(String venueId) {
            this.venueId = venueId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public VenueModel build() {
            return new VenueModel(venueId, name, description, location);
        }
    }
}

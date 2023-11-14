package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.LocationModel;

public class GetLocationResult {
    private final LocationModel location;

    private GetLocationResult(LocationModel location) {
        this.location = location;
    }

    public LocationModel getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "GetLocationResult{" + "location=" + location + '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocationModel location;

        public Builder withLocation(LocationModel location) {
            this.location = location;
            return this;
        }

        public GetLocationResult build() {
            return new GetLocationResult(location);
        }
    }
}

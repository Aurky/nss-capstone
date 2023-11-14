package com.nashss.se.bulletinboardservice.activity.requests;

public class GetLocationRequest {
    private final String locationId;

    private GetLocationRequest(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId() {
        return locationId;
    }

    @Override
    public String toString() {
        return "GetLocationRequest{" + "locationId=" + locationId + '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String locationId;

        public Builder withLocationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public GetLocationRequest build() {
            return new GetLocationRequest(locationId);
        }
    }
}

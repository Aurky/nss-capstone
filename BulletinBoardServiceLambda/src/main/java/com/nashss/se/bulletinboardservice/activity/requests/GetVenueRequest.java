package com.nashss.se.bulletinboardservice.activity.requests;

public class GetVenueRequest {
    private final String venueId;

    private GetVenueRequest(String venueId) {
        this.venueId = venueId;
    }

    public String getVenueId() {
        return venueId;
    }

    @Override
    public String toString() {
        return "GetVenueRequest{" + "venueId=" + venueId + '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String venueId;

        public Builder withVenueId(String venueId) {
            this.venueId = venueId;
            return this;
        }

        public GetVenueRequest build() {
            return new GetVenueRequest(venueId);
        }
    }
}


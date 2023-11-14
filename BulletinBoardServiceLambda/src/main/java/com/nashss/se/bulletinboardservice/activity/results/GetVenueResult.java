package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.VenueModel;

public class GetVenueResult {
    private final VenueModel venue;

    private GetVenueResult(VenueModel venue) {
        this.venue = venue;
    }

    public VenueModel getVenue() {
        return venue;
    }

    @Override
    public String toString() {
        return "GetVenueResult{" + "venue=" + venue + '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private VenueModel venue;

        public Builder withVenue(VenueModel venue) {
            this.venue = venue;
            return this;
        }

        public GetVenueResult build() {
            return new GetVenueResult(venue);
        }
    }
}

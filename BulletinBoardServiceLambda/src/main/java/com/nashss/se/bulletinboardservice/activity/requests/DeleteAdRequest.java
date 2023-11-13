package com.nashss.se.bulletinboardservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteAdRequest.Builder.class)
public class DeleteAdRequest {
    private final String adId;
    private final String userId;

    private DeleteAdRequest(String userId, String adId) {
        this.adId = adId;
        this.userId = userId;
    }

    public String getAdId() {
        return adId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "DeleteAdRequest{" +
                "adId='" + adId +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String adId;
        private String userId;
        public Builder withAdId(String adId) {
            this.adId = adId;
            return this;
        }
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public DeleteAdRequest build() {
            return new DeleteAdRequest(userId, adId);
        }
    }
}

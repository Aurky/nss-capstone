package com.nashss.se.bulletinboardservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = CreateAdRequest.Builder.class)
public class CreateAdRequest {
    private final String name;
    private final String adId;

    private CreateAdRequest(String name, String adId) {
        this.name = name;
        this.adId = adId;
    }

    public String getName() {
        return name;
    }

    public String getAdId() {
        return adId;
    }

    @Override
    public String toString() {
        return "CreateAdRequest{" +
                "name='" + name + '\'' +
                ", adId=" + adId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String adId;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAdId(String adId) {
            this.adId = adId;
            return this;
        }
        public CreateAdRequest build() {
            return new CreateAdRequest(name, adId);
        }
    }
}

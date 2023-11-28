package com.nashss.se.bulletinboardservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = CreateAdRequest.Builder.class)
public class CreateAdRequest {
    private final String name;
    private final String userId;

    private CreateAdRequest(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CreateAdRequest{" +
                "name='" + name + '\'' +
                ", userId=" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String userId;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public CreateAdRequest build() {
            return new CreateAdRequest(name, userId);
        }
    }
}

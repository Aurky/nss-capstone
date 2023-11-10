package com.nashss.se.bulletinboardservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateUserRequest.Builder.class)
public class CreateUserRequest {
    private final String name;
    private final String userId;

    private CreateUserRequest(String name, String userId) {
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
        return "CreateUserRequest{" +
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
        public CreateUserRequest build() {
            return new CreateUserRequest(name, userId);
        }
    }
}

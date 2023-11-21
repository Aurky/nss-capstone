package com.nashss.se.bulletinboardservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;

import java.util.List;

@JsonDeserialize(builder = UpdateUserRequest.Builder.class)
public class UpdateUserRequest {
    private final String userId;
    private final String name;
    private final String bio;
    private final List<String> groups;
    private final List<Ad> ads;
    private final List<String> roles;

    private UpdateUserRequest(String userId, String name, String bio, List<String> groups, List<Ad> ads, List<String> roles) {
        this.userId = userId;
        this.name = name;
        this.bio = bio;
        this.groups = groups;
        this.ads = ads;
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userId='" + userId + '\'' +
                "name='" + name + '\'' +
                "bio='" + bio + '\'' +
                "groups='" + groups + '\'' +
                "ads='" + ads + '\'' +
                "roles='" + roles + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String name;
        private String bio;
        private List<String> groups;
        private List<Ad> ads;
        private List<String> roles;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withBio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder withGroups(List<String> groups) {
            this.groups = groups;
            return this;
        }

        public Builder withAds(List<Ad> ads) {
            this.ads = ads;
            return this;
        }

        public Builder withRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UpdateUserRequest build() {
            return new UpdateUserRequest(userId, name, bio, groups, ads, roles);
        }
    }
}

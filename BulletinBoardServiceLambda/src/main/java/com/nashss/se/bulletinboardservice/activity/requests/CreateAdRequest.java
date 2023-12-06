package com.nashss.se.bulletinboardservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;


@JsonDeserialize(builder = CreateAdRequest.Builder.class)
public class CreateAdRequest {
    private final String name;
    private final String userId;
    private final String adId;
    private final String description;
    private final String location;
    private final String venue;
    private final Double salary;
    private final Set<String> tags;

    private CreateAdRequest(String name, String userId, String adId, String description, String location, String venue, Double salary, Set<String> tags) {
        this.name = name;
        this.userId = userId;
        this.adId = adId;
        this.description = description;
        this.location = location;
        this.venue = venue;
        this.salary = salary;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
    public String getAdId() {
        return adId;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getVenue() {
        return venue;
    }

    public Double getSalary() {
        return salary;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "CreateAdRequest{" +
                "name='" + name + '\'' +
                ", userId=" + userId + '\'' +
                ", adId=" + adId + '\'' +
                ", description=" + description + '\'' +
                ", location=" + location + '\'' +
                ", venue=" + venue + '\'' +
                ", salary=" + salary + '\'' +
                ", tags=" + tags + '\'' +
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
        private String adId;
        private String description;
        private String location;
        private String venue;
        private Double salary;
        private Set<String> tags;


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withAdId(String adId) {
            this.adId = adId;
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

        public Builder withVenue(String venue) {
            this.venue = venue;
            return this;
        }

        public Builder withSalary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder withTags(Set<String> tags) {
            this.tags = tags;
            return this;
        }


        public CreateAdRequest build() {
            return new CreateAdRequest(name, userId, adId, description, location, venue, salary, tags);
        }
    }
}

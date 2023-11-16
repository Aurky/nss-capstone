package com.nashss.se.bulletinboardservice.models;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;

import java.util.*;

public class AdModel {
    private final String adId;
    private final String name;
    private final String description;
    private final Double salary;
    private final String location;
    private final String venue;
    private final String userId;
    private final Set<String> tags;

    private AdModel(String adId, String name, String description, Double salary, String location, String venue, String userId, Set<String> tags) {
        this.adId = adId;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.venue = venue;
        this.userId = userId;
        this.tags = tags;
    }

    public String getAdId() {
        return adId;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getSalary() {
        return salary;
    }

    public String getLocation() {
        return location;
    }

    public String getVenue() {
        return venue;
    }

    public Set<String> getTags() {
        return tags;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdModel that = (AdModel) o;

        return  Objects.equals(userId, that.userId) && Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) && Objects.equals(salary, that.salary) &&
                Objects.equals(location, that.location) && Objects.equals(venue, that.venue) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, name, description, salary, location, venue, userId, tags);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String adId;
        private String name;
        private String description;
        private Double salary;
        private String location;
        private String venue;
        private String userId;
        private Set<String> tags;

        public Builder withAdId(String adId) {
            this.adId = adId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withSalary(Double salary) {
            this.salary = salary;
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

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withTags(Set<String> tags) {
            if (null == tags) {
                this.tags = null;
            } else {
                this.tags = new HashSet<>(tags);
            }
            return this;
        }

        public AdModel build() {
            return new AdModel(adId, name, description, salary, location, venue, userId, tags);
        }
    }
}
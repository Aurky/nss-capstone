package com.nashss.se.bulletinboardservice.models;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserModel {
    private final String userId;
    private final String name;
    private final String bio;
    private final List<String> groups;
    private final List<Ad> ads;
    private final List<String> roles;

    private UserModel(String userId, String name, String bio, List<String> groups, List<Ad> ads, List<String> roles) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserModel that = (UserModel) o;

        return  Objects.equals(userId, that.userId) && Objects.equals(name, that.name) &&
                Objects.equals(bio, that.bio) && Objects.equals(ads, that.ads) && Objects.equals(groups, that.groups) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, bio, groups, ads, roles);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

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

        public Builder withAds(List<Ad> ads) {
            if (null == ads) {
                this.ads = null;
            } else {
                this.ads = new ArrayList<>(ads);
            }
            return this;
        }

        public Builder withGroups(List<String> groups) {
            if (null == groups) {
                this.groups = null;
            } else {
                this.groups = new ArrayList<>(groups);
            }
            return this;
        }

        public Builder withRoles(List<String> roles) {
            if (null == roles) {
                this.roles = null;
            } else {
                this.roles = new ArrayList<>(roles);
            }
            return this;
        }

        public UserModel build() {
            return new UserModel(userId, name, bio, groups, ads, roles);
        }
    }


}

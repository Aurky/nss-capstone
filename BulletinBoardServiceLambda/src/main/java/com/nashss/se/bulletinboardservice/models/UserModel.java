package com.nashss.se.bulletinboardservice.models;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;

import java.util.List;

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


}

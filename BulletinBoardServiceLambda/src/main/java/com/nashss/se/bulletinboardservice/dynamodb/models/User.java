package com.nashss.se.bulletinboardservice.dynamodb.models;

import com.nashss.se.bulletinboardservice.converters.ModelConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "users")
public class User {
    private String userId;
    private String name;
    private String bio;
    private List<String> groups;
    private List<Ad> ads;
    private List<String> roles;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "bio")
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    @DynamoDBAttribute(attributeName = "groups")
    public List<String> getGroups() {
        return groups;
    }
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    @DynamoDBAttribute(attributeName = "ads")
    public List<Ad> getAds() {
        return ads;
    }
    public void setGroups(List<Ad> ads) {
        this.ads = ads;
    }

    @DynamoDBAttribute(attributeName = "roles")
    public List<String> getRoles() {
        return roles;
    }
    public void setGroups(List<String> roles) {
        this.roles = roles;
    }
}

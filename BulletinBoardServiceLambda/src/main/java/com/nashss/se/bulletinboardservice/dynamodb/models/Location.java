package com.nashss.se.bulletinboardservice.dynamodb.models;

import com.nashss.se.bulletinboardservice.converters.ModelConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.List;

@DynamoDBTable(tableName = "locations")
public class Location {
    private String locationId;
    private String name;
    private List<Venue> venues;

    @DynamoDBHashKey(attributeName = "locationId")
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "venues")
    public List<Venue> getVenues() {
        return venues;
    }
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }





}

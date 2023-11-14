package com.nashss.se.bulletinboardservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.List;

@DynamoDBTable(tableName = "venues")
public class Venue {
    private String venueId;
    private String name;
    private String description;
    private String location;

    @DynamoDBHashKey(attributeName = "venueId")
    public String getVenueId() {
        return venueId;
    }
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}

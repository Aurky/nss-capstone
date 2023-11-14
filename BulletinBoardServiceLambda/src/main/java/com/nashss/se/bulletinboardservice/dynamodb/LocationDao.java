package com.nashss.se.bulletinboardservice.dynamodb;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.exceptions.LocationNotFoundException;
import com.nashss.se.bulletinboardservice.exceptions.UserNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsConstants;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocationDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public LocationDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Location getLocation(String locationId) {
        Location location = this.dynamoDbMapper.load(Location.class, locationId);

        if (location == null) {
            metricsPublisher.addCount(MetricsConstants.GETLOCATION_LOCATIONNOTFOUND_COUNT, 1);
            throw new LocationNotFoundException("Could not find location with id " + locationId);

        }
        metricsPublisher.addCount(MetricsConstants.GETLOCATION_LOCATIONNOTFOUND_COUNT, 0);
        return location;
    }
}

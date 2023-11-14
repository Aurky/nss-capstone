package com.nashss.se.bulletinboardservice.dynamodb;

import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.dynamodb.models.Venue;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.exceptions.VenueNotFoundException;
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
public class VenueDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public VenueDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Venue getVenue(String venueId) {
        Venue venue = this.dynamoDbMapper.load(Venue.class, venueId);

        if (venue == null) {
            metricsPublisher.addCount(MetricsConstants.GETVENUE_VENUENOTFOUND_COUNT, 1);
            throw new VenueNotFoundException("Could not find venue with id " + venueId);

        }
        metricsPublisher.addCount(MetricsConstants.GETVENUE_VENUENOTFOUND_COUNT, 0);
        return venue;
    }
}

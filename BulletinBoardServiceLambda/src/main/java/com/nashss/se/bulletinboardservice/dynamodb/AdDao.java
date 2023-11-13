package com.nashss.se.bulletinboardservice.dynamodb;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.exceptions.AdNotFoundException;
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
public class AdDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates an AdDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public AdDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link Ad} corresponding to the specified id.
     *
     * @param adId the Ad ID
     * @return the stored Ad, or null if none was found.
     */
    public Ad getAd(String adId) {
        Ad ad = this.dynamoDbMapper.load(Ad.class, adId);

        if (ad == null) {
            metricsPublisher.addCount(MetricsConstants.GETAD_ADNOTFOUND_COUNT, 1);
            throw new UserNotFoundException("Could not find ad with id " + adId);

        }
        metricsPublisher.addCount(MetricsConstants.GETAD_ADNOTFOUND_COUNT, 0);
        return ad;
    }

    /**
     * Saves (creates or updates) the given ad.
     *
     * @param user The ad to save
     * @return The Ad object that was saved
     */
    public Ad saveAd(Ad ad) {
        this.dynamoDbMapper.save(ad);
        return ad;
    }
}

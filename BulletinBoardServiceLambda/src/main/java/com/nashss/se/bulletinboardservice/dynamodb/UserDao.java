package com.nashss.se.bulletinboardservice.dynamodb;

import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.exceptions.UserNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsConstants;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a pantry using {@link User} to represent the model in DynamoDB.
 */
@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a UserDao object.
     *
     * @param dynamoDbMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public UserDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Returns the {@link User} corresponding to the specified id.
     *
     * @param userId the User ID
     * @return the stored User, or null if none was found.
     */
    public User getUser(String userId) {
        User user = this.dynamoDbMapper.load(User.class, userId);

        if (user == null) {
            metricsPublisher.addCount(MetricsConstants.GETUSER_USERNOTFOUND_COUNT, 1);
            throw new UserNotFoundException("Could not find user with id " + userId);

        }
        metricsPublisher.addCount(MetricsConstants.GETUSER_USERNOTFOUND_COUNT, 0);
        return user;
    }

    /**
     * Saves (creates or updates) the given user.
     *
     * @param user The user to save
     * @return The User object that was saved
     */
    public User saveUser(User user) {
        this.dynamoDbMapper.save(user);
        return user;
    }

//    public List<String> getUserGroups() {
//        User user = new User
//    }
//
//    public List<String> getUserRoles() {
//
//    }
}

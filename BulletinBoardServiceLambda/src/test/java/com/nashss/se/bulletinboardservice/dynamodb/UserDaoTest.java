package com.nashss.se.bulletinboardservice.dynamodb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.exceptions.AdNotFoundException;
import com.nashss.se.bulletinboardservice.exceptions.LocationNotFoundException;
import com.nashss.se.bulletinboardservice.exceptions.UserNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;
import com.nashss.se.bulletinboardservice.models.LocationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class UserDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;

    @InjectMocks
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        userDao = new UserDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    void getUser_returnUser() {
        User user = new User();
        user.setUserId("user123");

        when(dynamoDBMapper.load(eq(User.class), anyString())).thenReturn(user);

        User result = userDao.getUser("user123");

        assertNotNull(result);
        assertEquals("user123", result.getUserId());
    }

    @Test
    void getLocation_NonExistingAd_ThrowsLocationNotFoundException() {

        when(dynamoDBMapper.load(eq(User.class), anyString())).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userDao.getUser("nonExistingUserId"));
    }

}
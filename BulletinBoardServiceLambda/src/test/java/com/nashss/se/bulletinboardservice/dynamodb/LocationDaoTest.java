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
import com.nashss.se.bulletinboardservice.exceptions.AdNotFoundException;
import com.nashss.se.bulletinboardservice.exceptions.LocationNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;
import com.nashss.se.bulletinboardservice.models.LocationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class LocationDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;

    @InjectMocks
    private LocationDao locationDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        locationDao = new LocationDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    void getLocation_returnLocation() {
        Location location = new Location();
        location.setLocationId("location123");

        when(dynamoDBMapper.load(eq(Location.class), anyString())).thenReturn(location);

        Location result = locationDao.getLocation("location123");

        assertNotNull(result);
        assertEquals("location123", result.getLocationId());
    }

    @Test
    void getLocation_NonExistingLocation_ThrowsLocationNotFoundException() {

        when(dynamoDBMapper.load(eq(Location.class), anyString())).thenReturn(null);

        assertThrows(LocationNotFoundException.class, () -> locationDao.getLocation("nonExistingLocationId"));
    }

}
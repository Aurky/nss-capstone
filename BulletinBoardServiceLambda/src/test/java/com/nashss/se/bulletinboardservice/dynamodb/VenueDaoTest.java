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
import com.nashss.se.bulletinboardservice.dynamodb.models.Venue;
import com.nashss.se.bulletinboardservice.exceptions.AdNotFoundException;
import com.nashss.se.bulletinboardservice.exceptions.LocationNotFoundException;
import com.nashss.se.bulletinboardservice.exceptions.VenueNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;
import com.nashss.se.bulletinboardservice.models.LocationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class VenueDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;

    @InjectMocks
    private VenueDao venueDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        venueDao = new VenueDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    void getVenue_returnVenue() {
        Venue venue = new Venue();
        venue.setVenueId("venue123");

        when(dynamoDBMapper.load(eq(Venue.class), anyString())).thenReturn(venue);

        Venue result = venueDao.getVenue("venue123");

        assertNotNull(result);
        assertEquals("venue123", result.getVenueId());
    }

    @Test
    void getVenue_NonExistingVenue_ThrowsVenueNotFoundException() {

        when(dynamoDBMapper.load(eq(Venue.class), anyString())).thenReturn(null);

        assertThrows(VenueNotFoundException.class, () -> venueDao.getVenue("nonExistingVenueId"));
    }

}
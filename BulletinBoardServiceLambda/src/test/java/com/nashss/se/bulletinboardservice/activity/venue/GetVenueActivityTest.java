package com.nashss.se.bulletinboardservice.activity.venue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.location.GetLocationActivity;
import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetLocationRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetVenueRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetLocationResult;
import com.nashss.se.bulletinboardservice.activity.results.GetVenueResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.VenueDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.dynamodb.models.Venue;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;

class GetVenueActivityTest {

    @Mock
    private VenueDao venueDao;

    @InjectMocks
    private GetVenueActivity getVenueActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {

        String venueId = "venue123";
        getVenueActivity = new GetVenueActivity(venueDao);

        Venue venue = new Venue();
        venue.setVenueId(venueId);

        GetVenueRequest getVenueRequest = new GetVenueRequest.Builder()
                .withVenueId(venueId)
                .build();

        when(venueDao.getVenue(anyString())).thenReturn(venue);

        GetVenueResult result = getVenueActivity.handleRequest(getVenueRequest);

        assertNotNull(result);
        assertNotNull(result.getVenue());
        assertEquals(venueId, result.getVenue().getVenueId());
    }

}
package com.nashss.se.bulletinboardservice.activity.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetLocationRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetLocationResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;

class GetLocationActivityTest {

    @Mock
    private LocationDao locationDao;

    @InjectMocks
    private GetLocationActivity getLocationActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {
        getLocationActivity = new GetLocationActivity(locationDao);

        Location location = new Location();
        location.setLocationId("testLocationId");
        location.setName("Test Location");
        location.setVenues(new ArrayList<>());

        GetLocationRequest getLocationRequest = new GetLocationRequest.Builder()
                .withLocationId("testLocationId")
                .build();

        when(locationDao.getLocation(anyString())).thenReturn(location);

        GetLocationResult result = getLocationActivity.handleRequest(getLocationRequest);

        assertNotNull(result);
        assertNotNull(result.getLocation());
        assertEquals("testLocationId", result.getLocation().getLocationId());

        verify(locationDao, times(1)).getLocation(anyString());
    }
}
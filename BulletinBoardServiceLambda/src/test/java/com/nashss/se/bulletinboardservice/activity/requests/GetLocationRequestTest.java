package com.nashss.se.bulletinboardservice.activity.requests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.location.GetLocationActivity;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetLocationResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


class GetLocationRequestTest {

    @Mock
    private LocationDao locationDao;

    @InjectMocks
    private GetLocationRequest getLocationRequest;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {

        String locationId = "location123";

        getLocationRequest = GetLocationRequest.builder()
                .withLocationId("location123")
                .build();

//        Location location = new Location();
//        location.setLocationId("location123");
//
//        when(locationDao.getLocation("location123")).thenReturn(location);
//
//        GetLocationActivity getLocationActivity = new GetLocationActivity(locationDao);
//
//        GetLocationResult result = getLocationActivity.handleRequest(getLocationRequest);

        assertEquals(locationId, getLocationRequest.getLocationId());
    }

}
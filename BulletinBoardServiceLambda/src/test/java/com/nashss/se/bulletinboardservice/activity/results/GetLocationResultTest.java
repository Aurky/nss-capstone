package com.nashss.se.bulletinboardservice.activity.results;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.models.LocationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GetLocationResultTest {

    @Mock
    private LocationModel locationModel;

    @InjectMocks
    private GetLocationResult getLocationResult;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {

        String locationId = "location123";

        locationModel = new LocationModel.Builder()
                .withLocationId(locationId)
                .build();

        getLocationResult = new GetLocationResult.Builder()
                .withLocation(locationModel)
                .build();

        assertEquals(locationId, getLocationResult.getLocation().getLocationId());
    }

}
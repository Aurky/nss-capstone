package com.nashss.se.bulletinboardservice.activity.requests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.VenueDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GetVenueRequestTest {

    @Mock
    private VenueDao venueDao;

    @InjectMocks
    private GetVenueRequest getVenueRequest;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {

        String venueId = "venue123";

        getVenueRequest = new GetVenueRequest.Builder()
                .withVenueId("venue123")
                .build();

        assertNotNull(getVenueRequest);
        assertEquals(venueId, getVenueRequest.getVenueId());
    }
}
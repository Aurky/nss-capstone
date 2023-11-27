package com.nashss.se.bulletinboardservice.activity.ad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.requests.UpdateAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.UpdateAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

class UpdateAdActivityTest {

    @Mock
    private AdDao adDao;

    @InjectMocks
    private UpdateAdActivity updateAdActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {
        UpdateAdActivity updateAdActivity = new UpdateAdActivity(adDao);

        Ad testAd = new Ad();
        testAd.setAdId("testAdId");
        testAd.setName("Old Name");
        testAd.setUserId("testUser");
        testAd.setTags(new HashSet<>());

        UpdateAdRequest updateAdRequest = new UpdateAdRequest.Builder()
                .withUserId("testUser")
                .withAdId("testAdId")
                .withName("New Name")
                .withDescription("New Description")
                .withSalary(1000.0)
                .withLocation("New Location")
                .withVenue("New Venue")
                .withTags(new HashSet<>())
                .build();

        when(adDao.getAd(anyString(), anyString())).thenReturn(testAd);
        when(adDao.saveAd(any())).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateAdResult result = updateAdActivity.handleRequest(updateAdRequest);

        assertNotNull(result);
        assertNotNull(result.getAd());
        assertEquals("New Name", result.getAd().getName());

        verify(adDao, times(1)).getAd(anyString(), anyString());
        verify(adDao, times(1)).saveAd(any(Ad.class));
    }
}
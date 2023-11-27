package com.nashss.se.bulletinboardservice.activity.ad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

class GetAdActivityTest {

    @Mock
    private AdDao adDao;

    @InjectMocks
    private GetAdActivity getAdActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }
    @Test
    void handleRequest() {
        getAdActivity = new GetAdActivity(adDao);

        Ad testAd = new Ad();
        testAd.setAdId("testAdId");
        testAd.setName("Test Ad");
        testAd.setUserId("testUser");
        testAd.setTags(new HashSet<>());

        GetAdRequest getAdRequest = new GetAdRequest.Builder()
                .withUserId("testUser")
                .withAdId("testAdId")
                .build();

        when(adDao.getAd(anyString(), anyString())).thenReturn(testAd);

        GetAdResult result = getAdActivity.handleRequest(getAdRequest);

        assertNotNull(result);
        assertNotNull(result.getAd());
        assertEquals("Test Ad", result.getAd().getName());

        verify(adDao, times(1)).getAd(anyString(), anyString());

    }

}
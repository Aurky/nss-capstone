package com.nashss.se.bulletinboardservice.activity.requests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GetAdRequestTest {

    @Mock
    private AdDao adDao;

    @InjectMocks
    private GetAdRequest getAdRequest;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void getAdRequest_HandleRequest_ReturnsGetAdResult() {

        getAdRequest = GetAdRequest.builder()
                .withUserId("user123")
                .withAdId("ad456")
                .build();

        Ad sampleAd = new Ad();
        sampleAd.setAdId("ad456");
        sampleAd.setUserId("user123");

        when(adDao.getAd("user123", "ad456")).thenReturn(sampleAd);

        // Create GetAdActivity instance
        GetAdActivity getAdActivity = new GetAdActivity(adDao);

        // Call handleRequest method
        GetAdResult result = getAdActivity.handleRequest(getAdRequest);

        // Assertions
        assertNotNull(result);
        assertNotNull(result.getAd());
        assertEquals("ad456", result.getAd().getAdId());
        assertEquals("user123", result.getAd().getUserId());
        verify(adDao, times(1)).getAd("user123", "ad456");
    }
}
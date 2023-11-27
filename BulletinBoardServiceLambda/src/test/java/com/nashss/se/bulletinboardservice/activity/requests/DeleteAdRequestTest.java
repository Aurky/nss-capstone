package com.nashss.se.bulletinboardservice.activity.requests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.DeleteAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.DeleteAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.DeleteAdResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class DeleteAdRequestTest {

    @Mock
    private AdDao adDao;

    @InjectMocks
    private DeleteAdRequest deleteAdRequest;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void deleteAdRequest_HandleRequest_ReturnsDeleteAdResult() {
        deleteAdRequest = DeleteAdRequest.builder()
                .withUserId("user123")
                .withAdId("ad456")
                .build();

        Ad sampleAd = new Ad();
        sampleAd.setAdId("ad456");
        sampleAd.setUserId("user123");

        // Mock behavior
        when(adDao.getAd("user123", "ad456")).thenReturn(sampleAd);

        // Create DeleteAdActivity instance
        DeleteAdActivity deleteAdActivity = new DeleteAdActivity(adDao);

        // Call handleRequest method
        DeleteAdResult result = deleteAdActivity.handleRequest(deleteAdRequest);

        // Assertions
        assertNotNull(result);
        assertNotNull(result.getAd());
        verify(adDao, times(1)).getAd("user123", "ad456");
        verify(adDao, times(1)).deleteAd(sampleAd);
    }
}
package com.nashss.se.bulletinboardservice.activity.ad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.DeleteAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.activity.results.DeleteAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

class DeleteAdActivityTest {

    @Mock
    private AdDao adDao;

    @InjectMocks
    private DeleteAdActivity deleteAdActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {
        deleteAdActivity = new DeleteAdActivity(adDao);

        // Create a test Ad
        Ad testAd = new Ad();
        testAd.setAdId("testAdId");
        testAd.setName("Test Ad");
        testAd.setUserId("testUser");
        testAd.setTags(new HashSet<>());

        DeleteAdRequest deleteAdRequest = new DeleteAdRequest.Builder()
                .withUserId("testUser")
                .withAdId("testAdId")
                .build();

        when(adDao.getAd(anyString(), anyString())).thenReturn(testAd);

        DeleteAdResult result = deleteAdActivity.handleRequest(deleteAdRequest);

        assertNotNull(result);
        assertNotNull(result.getAd());
        assertEquals("Test Ad", result.getAd().getName());

        verify(adDao, times(1)).getAd(anyString(), anyString());
        verify(adDao, times(1)).deleteAd(any(Ad.class));

    }
}
package com.nashss.se.bulletinboardservice.activity.requests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.CreateAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.models.AdModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CreateAdRequestTest {

    @Mock
    private AdDao adDao;

    @InjectMocks
    private CreateAdRequest createAdRequest;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void createAdRequest_HandleRequest_ReturnsCreateAdResult() {

        createAdRequest = CreateAdRequest.builder()
                .withName("Test Ad")
                .withUserId("testUserId")
                .build();


        when(adDao.saveAd(any(Ad.class))).thenAnswer(invocation -> {
            Ad savedAd = invocation.getArgument(0);
            savedAd.setAdId("testAdId");
            return savedAd;
        });

        CreateAdActivity createAdActivity = new CreateAdActivity(adDao);
        CreateAdResult result = createAdActivity.handleRequest(createAdRequest);


        assertNotNull(result);
        AdModel adModel = result.getAd();
        assertNotNull(adModel);
        assertEquals("Test Ad", adModel.getName());
        assertEquals("testUserId", adModel.getUserId());
        assertNotNull(adModel.getAdId());

        verify(adDao, times(1)).saveAd(any(Ad.class));
    }
}
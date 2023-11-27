package com.nashss.se.bulletinboardservice.dynamodb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.exceptions.AdNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsConstants;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AdDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;

    @InjectMocks
    private AdDao adDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        adDao = new AdDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    void getAd_ExistingAd_ReturnsAd() {

        Ad testAd = new Ad();
        testAd.setAdId("testAdId");
        testAd.setUserId("testUserId");

        when(dynamoDBMapper.load(eq(Ad.class), anyString(), anyString())).thenReturn(testAd);

        Ad result = adDao.getAd("testUserId", "testAdId");

        assertNotNull(result);
        assertEquals("testAdId", result.getAdId());
        assertEquals("testUserId", result.getUserId());
    }

    @Test
    void getAd_NonExistingAd_ThrowsAdNotFoundException() {

        when(dynamoDBMapper.load(eq(Ad.class), anyString(), anyString())).thenReturn(null);

        assertThrows(AdNotFoundException.class, () -> adDao.getAd("testUserId", "nonExistingAdId"));
    }


}
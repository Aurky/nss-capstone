package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.models.AdModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class GetAdResultTest {

    @Mock
    private AdModel adModel;

    @InjectMocks
    private GetAdResult getAdResult;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void testGetAdResult() {

        adModel = AdModel.builder()
                .withAdId("sampleAdId")
                .withName("Sample Ad")
                .withDescription("Sample description")
                .withSalary(0.0)
                .withLocation("Sample Location")
                .withVenue("Sample Venue")
                .withUserId("sampleUserId")
                .withTags(new HashSet<>())
                .build();

        getAdResult = GetAdResult.builder()
                .withAd(adModel)
                .build();

        assertEquals(adModel, getAdResult.getAd());
    }
}
package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.AdModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateAdResultTest {

    @Mock
    private AdModel adModel;

    @InjectMocks
    private UpdateAdResult updateAdResult;

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

        updateAdResult = UpdateAdResult.builder()
                .withAd(adModel)
                .build();

        assertEquals(adModel, updateAdResult.getAd());
    }

}
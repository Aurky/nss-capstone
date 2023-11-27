package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.models.AdModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateAdResultTest {

    @Mock
    private AdModel adModel;

    @InjectMocks
    private CreateAdResult createAdResult;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void testCreateAdResult() {

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

        // Create CreateAdResult using the sample AdModel
        createAdResult = CreateAdResult.builder()
                .withAd(adModel)
                .build();

        // Verify that the CreateAdResult returns the sample AdModel
        assertEquals(adModel, createAdResult.getAd());
    }
}
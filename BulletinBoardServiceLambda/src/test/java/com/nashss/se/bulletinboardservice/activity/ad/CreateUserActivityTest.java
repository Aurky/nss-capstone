package com.nashss.se.bulletinboardservice.activity.ad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

public class CreateUserActivityTest {

    @Mock
    private AdDao adDao;


    @InjectMocks
    private CreateAdActivity createAdActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testHandleRequest() {
        CreateAdRequest createAdRequest = new CreateAdRequest.Builder()
                .withName("test")
                .withUserId("testUser")
                .build();

        Ad newAd = new Ad();
        newAd.setAdId("");
        newAd.setName(createAdRequest.getName());
        newAd.setDescription("");
        newAd.setSalary(0.0);
        newAd.setLocation("");
        newAd.setVenue("");
        newAd.setUserId(createAdRequest.getUserId());
        newAd.setTags(new HashSet<>());

        when(adDao.saveAd(any(Ad.class))).thenReturn(newAd);

        CreateAdResult createAdResult = createAdActivity.handleRequest(createAdRequest);

        assertEquals("testUser", createAdResult.getAd().getUserId());
    }
}
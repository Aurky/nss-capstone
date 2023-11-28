package com.nashss.se.bulletinboardservice.activity.results;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.models.LocationModel;
import com.nashss.se.bulletinboardservice.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


class CreateUserResultTest {

    @Mock
    private UserModel userModel;

    @InjectMocks
    private CreateUserResult createUserResult;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void handleRequest() {
        String userId = "user123";

        userModel = new UserModel.Builder()
                .withUserId(userId)
                .build();

        createUserResult = new CreateUserResult.Builder()
                .withUser(userModel)
                .build();

        assertNotNull(userModel);
        assertNotNull(createUserResult);
        assertEquals(userId, createUserResult.getUser().getUserId());
    }
}
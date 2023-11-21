package com.nashss.se.bulletinboardservice.activity.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.nashss.se.bulletinboardservice.activity.requests.CreateUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateUserResult;
import com.nashss.se.bulletinboardservice.dynamodb.UserDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.models.UserModel;
import com.nashss.se.bulletinboardservice.utils.BullitenBoardServiceUtils;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class CreateUserActivityTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private CreateUserActivity createUserActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleRequest() {
        // Arrange
        CreateUserRequest createUserRequest = new CreateUserRequest.Builder()
                .withUserId("testUserId")
                .withName("Test User")
                .build();

        User newUser = new User();
        newUser.setUserId(createUserRequest.getUserId());
        newUser.setName(createUserRequest.getName());
        newUser.setBio("");
        newUser.setAds(new ArrayList<>());
        newUser.setGroups(new ArrayList<>());
        newUser.setRoles(new ArrayList<>());

        when(userDao.saveUser(any(User.class))).thenReturn(newUser);

        // Act
        CreateUserResult createUserResult = createUserActivity.handleRequest(createUserRequest);

        // Assert
        assertEquals("Test User", createUserResult.getUser().getName());
        // Add more assertions based on your requirements
    }
}

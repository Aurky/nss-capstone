package com.nashss.se.bulletinboardservice.activity.user;

import com.nashss.se.bulletinboardservice.activity.requests.CreateUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateUserResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.UserDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.models.UserModel;
import com.nashss.se.bulletinboardservice.utils.BullitenBoardServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import javax.inject.Inject;
public class CreateUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    /**
     * Instantiates a new CreateUserActivity object.
     *
     * @param userDao UserDao to access the pantries table.
     */
    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by persisting a new user
     * with the provided name and user ID from the request.
     * <p>
     * It then returns the newly created user.
     * <p>
     * If the provided name or user ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createUserRequest request object containing the name and user ID
     *                              associated with it
     * @return createUserResult result object containing the API defined {@link UserModel}
     */
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest) {
        log.info("Received CreateUserRequest {}", createUserRequest);

        User newUser = new User();
        newUser.setUserId(createUserRequest.getUserId());
        newUser.setName(createUserRequest.getName());
        newUser.setBio("");
        newUser.setAds(new ArrayList<>());
        newUser.setGroups(new ArrayList<>());
        newUser.setRoles(new ArrayList<>());

        userDao.saveUser(newUser);

        UserModel userModel = new ModelConverter().toUserModel(newUser);
        return CreateUserResult.builder()
                .withUser(userModel)
                .build();
    }


}

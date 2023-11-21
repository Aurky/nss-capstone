package com.nashss.se.bulletinboardservice.activity.user;

import com.nashss.se.bulletinboardservice.activity.requests.UpdateUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.UpdateUserResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.UserDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;
import com.nashss.se.bulletinboardservice.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public UpdateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    public UpdateUserResult handleRequest(final UpdateUserRequest updateUserRequest) {
        log.info("Received UpdateUserRequest {}", updateUserRequest);

        User user = userDao.getUser(updateUserRequest.getUserId());

        user.setName(updateUserRequest.getName());
        user.setBio(updateUserRequest.getBio());
        user.setGroups(updateUserRequest.getGroups());
        user.setAds(updateUserRequest.getAds());
        user.setRoles(updateUserRequest.getRoles());

        return UpdateUserResult.builder()
                .withUser(new ModelConverter().toUserModel(user))
                .build();
    }
}

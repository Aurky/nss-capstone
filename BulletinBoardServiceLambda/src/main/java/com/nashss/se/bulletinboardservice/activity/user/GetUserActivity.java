package com.nashss.se.bulletinboardservice.activity.user;

import com.nashss.se.bulletinboardservice.activity.requests.GetUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetUserResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.UserDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.models.UserModel;

import net.bytebuddy.matcher.StringMatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
public class GetUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public GetUserActivity(UserDao userDao) {this.userDao = userDao;}

    public GetUserResult handleRequest(final GetUserRequest getUserRequest) {
        log.info("Recieved GetUserRequest {}", getUserRequest);

        User result = userDao.getUser(getUserRequest.getUserId());
        UserModel userModel = new ModelConverter().toUserModel(result);

        return GetUserResult.builder()
                .withUser(userModel)
                .build();
    }
}

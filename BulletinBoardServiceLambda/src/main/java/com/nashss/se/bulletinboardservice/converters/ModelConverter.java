package com.nashss.se.bulletinboardservice.converters;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.models.UserModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    /**
     * Converts a provided {@link User} into a {@link UserModel} representation.
     *
     * @param user the user to convert
     * @return the converted user
     */
    public UserModel toUserModel(User user) {
        List<Ad> ads = null;
        if (user.getAds() != null) {
            ads = new ArrayList<>(user.getAds());
        }

        return UserModel.builder()
                .withUserId(user.getUserId())
                .withName(user.getName())
                .withBio(user.getBio())
                .withAds(user.getAds())
                .withGroups(user.getGroups())
                .withRoles(user.getRoles())
                .build();
    }
}

package com.nashss.se.bulletinboardservice.converters;

import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.dynamodb.models.Venue;
import com.nashss.se.bulletinboardservice.models.AdModel;
import com.nashss.se.bulletinboardservice.models.LocationModel;
import com.nashss.se.bulletinboardservice.models.UserModel;
import com.nashss.se.bulletinboardservice.models.VenueModel;

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

    public AdModel toAdModel(Ad ad) {
        return AdModel.builder()
                .withAdId(ad.getAdId())
                .withName(ad.getName())
                .withDescription(ad.getDescription())
                .withSalary(ad.getSalary())
                .withLocation(ad.getLocation())
                .withVenue(ad.getVenue())
                .withUserId(ad.getUserId())
                .withTags(ad.getTags())
                .build();
    }

    public VenueModel toVenueModel(Venue venue) {
        return VenueModel.builder()
                .withVenueId(venue.getVenueId())
                .withName(venue.getName())
                .withDescription(venue.getDescription())
                .withLocation(venue.getLocation())
                .build();
    }

    public LocationModel toLocationModel(Location location) {
        return LocationModel.builder()
                .withLocationId(location.getLocationId())
                .withName(location.getName())
                .withVenues(location.getVenues())
                .build();
    }
}

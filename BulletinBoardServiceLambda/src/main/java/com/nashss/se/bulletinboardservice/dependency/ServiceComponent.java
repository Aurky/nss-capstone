package com.nashss.se.bulletinboardservice.dependency;

import com.amazonaws.services.dynamodbv2.model.Delete;
import com.nashss.se.bulletinboardservice.activity.ad.*;
import com.nashss.se.bulletinboardservice.activity.location.GetLocationActivity;
import com.nashss.se.bulletinboardservice.activity.user.CreateUserActivity;

import com.nashss.se.bulletinboardservice.activity.user.GetUserActivity;
import com.nashss.se.bulletinboardservice.activity.user.UpdateUserActivity;
import com.nashss.se.bulletinboardservice.activity.venue.GetVenueActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity
     * @return CreateUserActivity
     */
    CreateUserActivity provideCreateUserActivity();

    CreateAdActivity provideCreateAdActivity();

    GetAdActivity provideGetAdActivity();

    UpdateAdActivity provideUpdateAdActivity();

    DeleteAdActivity provideDeleteAdActivity();

    GetVenueActivity provideGetVenueActivity();

    GetLocationActivity provideGetLocationActivity();

    GetUserActivity provideGetUserActivity();

    UpdateUserActivity provideUpdateUserActivity();

    GetUserAdsActivity provideGetUserAdsActivity();
}

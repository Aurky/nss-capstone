package com.nashss.se.bulletinboardservice.dependency;

import com.nashss.se.bulletinboardservice.activity.ad.CreateAdActivity;
import com.nashss.se.bulletinboardservice.activity.ad.GetAdActivity;
import com.nashss.se.bulletinboardservice.activity.ad.UpdateAdActivity;
import com.nashss.se.bulletinboardservice.activity.user.CreateUserActivity;

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
}

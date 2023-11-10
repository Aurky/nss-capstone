package com.nashss.se.bulletinboardservice.dependency;

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
}
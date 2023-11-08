package com.nashss.se.bulletinboardservice.dynamodb;

import com.nashss.se.bulletinboardservice.dynamodb.models.User;
import com.nashss.se.bulletinboardservice.exceptions.UserNotFoundException;
import com.nashss.se.bulletinboardservice.metrics.MetricsConstants;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

public class UserDao {
}

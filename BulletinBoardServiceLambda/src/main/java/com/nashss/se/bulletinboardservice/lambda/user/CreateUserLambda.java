package com.nashss.se.bulletinboardservice.lambda.user;

import com.nashss.se.bulletinboardservice.activity.requests.CreateUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateUserResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;


public class CreateUserLambda
        extends LambdaActivityRunner<CreateUserRequest, CreateUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateUserRequest unauthenticatedRequest = input.fromBody(CreateUserRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateUserRequest.builder()
                                    .withName(unauthenticatedRequest.getName())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateUserActivity().handleRequest(request)
        );
    }
}

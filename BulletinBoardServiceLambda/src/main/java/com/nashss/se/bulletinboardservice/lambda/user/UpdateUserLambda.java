package com.nashss.se.bulletinboardservice.lambda.user;

import com.nashss.se.bulletinboardservice.activity.user.UpdateUserActivity;
import com.nashss.se.bulletinboardservice.activity.requests.UpdateAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.UpdateUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.UpdateAdResult;
import com.nashss.se.bulletinboardservice.activity.results.UpdateUserResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;

public class UpdateUserLambda extends LambdaActivityRunner<UpdateUserRequest, UpdateUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateUserRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateUserRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateUserRequest unauthenticatedRequest = input.fromBody(UpdateUserRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateUserRequest.builder()
                                    .withUserId(claims.get("email"))
                                    .withName(unauthenticatedRequest.getName())
                                    .withBio(unauthenticatedRequest.getBio())
                                    .withGroups(unauthenticatedRequest.getGroups())
                                    .withAds(unauthenticatedRequest.getAds())
                                    .withRoles(unauthenticatedRequest.getRoles())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateUserActivity().handleRequest(request)
        );
    }
}

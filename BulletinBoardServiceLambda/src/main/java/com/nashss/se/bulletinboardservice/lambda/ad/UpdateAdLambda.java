package com.nashss.se.bulletinboardservice.lambda.ad;

import com.nashss.se.bulletinboardservice.activity.ad.UpdateAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.UpdateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.UpdateAdResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;

public class UpdateAdLambda
        extends LambdaActivityRunner<UpdateAdRequest, UpdateAdResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateAdRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateAdRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateAdRequest unauthenticatedRequest = input.fromBody(UpdateAdRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateAdRequest.builder()
                                    .withAdId(unauthenticatedRequest.getAdId())
                                    .withName(unauthenticatedRequest.getName())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withLocation(unauthenticatedRequest.getLocation())
                                    .withVenue(unauthenticatedRequest.getVenue())
                                    .withSalary(unauthenticatedRequest.getSalary())
                                    .withTags(unauthenticatedRequest.getTags())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateAdActivity().handleRequest(request)
        );
    }
}

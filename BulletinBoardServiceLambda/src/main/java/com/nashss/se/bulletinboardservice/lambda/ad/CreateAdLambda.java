package com.nashss.se.bulletinboardservice.lambda.ad;

import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;


public class CreateAdLambda
        extends LambdaActivityRunner<CreateAdRequest, CreateAdResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateAdRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateAdRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateAdRequest unauthenticatedRequest = input.fromBody(CreateAdRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateAdRequest.builder()
                                    .withName(unauthenticatedRequest.getName())
                                    .withUserId(claims.get("email"))
                                    .withAdId(unauthenticatedRequest.getAdId())
                                    .withDescription(unauthenticatedRequest.getDescription())
                                    .withLocation(unauthenticatedRequest.getLocation())
                                    .withVenue(unauthenticatedRequest.getVenue())
                                    .withSalary(unauthenticatedRequest.getSalary())
                                    .withTags(unauthenticatedRequest.getTags())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateAdActivity().handleRequest(request)
        );
    }
}


package com.nashss.se.bulletinboardservice.lambda.ad;

import com.nashss.se.bulletinboardservice.activity.ad.DeleteAdActivity;
import com.nashss.se.bulletinboardservice.activity.requests.DeleteAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.UpdateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.DeleteAdResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.activity.results.UpdateAdResult;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;

public class DeleteAdLambda extends LambdaActivityRunner<DeleteAdRequest, DeleteAdResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteAdRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteAdRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    DeleteAdRequest unauthenticatedRequest = input.fromPath(path ->
                            DeleteAdRequest.builder()
                                    .withAdId(path.get("adId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            DeleteAdRequest.builder()
                                    .withUserId(claims.get("email"))
                                    .withAdId(unauthenticatedRequest.getAdId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteAdActivity().handleRequest(request)
        );
    }
}

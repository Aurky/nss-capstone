package com.nashss.se.bulletinboardservice.lambda.user;

import com.nashss.se.bulletinboardservice.activity.requests.GetUserRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetUserResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetUserLambda extends LambdaActivityRunner<GetUserRequest, GetUserResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetUserRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetUserRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    GetUserRequest unauthenticatedRequest = input.fromPath(path ->
                            GetUserRequest.builder()
                                    .withUserId(path.get("userId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            GetUserRequest.builder()
                                    .withUserId(unauthenticatedRequest.getUserId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetUserActivity().handleRequest(request)
        );
    }
}

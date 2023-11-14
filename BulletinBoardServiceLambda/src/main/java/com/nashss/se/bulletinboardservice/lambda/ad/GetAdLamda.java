package com.nashss.se.bulletinboardservice.lambda.ad;

import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAdLamda extends LambdaActivityRunner<GetAdRequest, GetAdResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAdRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAdRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    GetAdRequest unauthenticatedRequest = input.fromPath(path ->
                            GetAdRequest.builder()
                                    .withAdId(path.get("adId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            GetAdRequest.builder()
                                    .withAdId(unauthenticatedRequest.getAdId())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetAdActivity().handleRequest(request)
        );
    }
}

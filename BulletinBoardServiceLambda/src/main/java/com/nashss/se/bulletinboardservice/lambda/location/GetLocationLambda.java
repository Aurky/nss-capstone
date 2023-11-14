package com.nashss.se.bulletinboardservice.lambda.location;

import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.requests.GetLocationRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.activity.results.GetLocationResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetLocationLambda extends LambdaActivityRunner<GetLocationRequest, GetLocationResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetLocationRequest>, LambdaResponse> {


    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetLocationRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    GetLocationRequest unauthenticatedRequest = input.fromPath(path ->
                            GetLocationRequest.builder()
                                    .withLocationId(path.get("locationId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            GetLocationRequest.builder()
                                    .withLocationId(unauthenticatedRequest.getLocationId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetLocationActivity().handleRequest(request)
        );
    }
}

package com.nashss.se.bulletinboardservice.lambda.venue;

import com.nashss.se.bulletinboardservice.activity.requests.GetVenueRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetVenueResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetVenueLamda extends LambdaActivityRunner<GetVenueRequest, GetVenueResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetVenueRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetVenueRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> {
                    GetVenueRequest unauthenticatedRequest = input.fromPath(path ->
                            GetVenueRequest.builder()
                                    .withVenueId(path.get("venueId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            GetVenueRequest.builder()
                                    .withVenueId(unauthenticatedRequest.getVenueId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetVenueActivity().handleRequest(request)
        );
    }
}

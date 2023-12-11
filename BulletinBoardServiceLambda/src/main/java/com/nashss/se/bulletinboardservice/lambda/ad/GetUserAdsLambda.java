package com.nashss.se.bulletinboardservice.lambda.ad;



import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.bulletinboardservice.activity.requests.GetUserAdsRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetUserAdsResult;
import com.nashss.se.bulletinboardservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.bulletinboardservice.lambda.LambdaActivityRunner;
import com.nashss.se.bulletinboardservice.lambda.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetUserAdsLambda extends LambdaActivityRunner<GetUserAdsRequest, GetUserAdsResult> implements RequestHandler<AuthenticatedLambdaRequest<GetUserAdsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetUserAdsRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromUserClaims(claims ->
                        GetUserAdsRequest.builder()
                                .withUserId(claims.get("email"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetUserAdsActivity().handleRequest(request)
        );
    }
}

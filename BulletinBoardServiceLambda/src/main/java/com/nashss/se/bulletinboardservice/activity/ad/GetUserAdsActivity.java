package com.nashss.se.bulletinboardservice.activity.ad;


import com.nashss.se.bulletinboardservice.activity.requests.GetUserAdsRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetUserAdsResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.models.AdModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;


public class GetUserAdsActivity {
    private final Logger log = LogManager.getLogger();
    private final AdDao adDao;

    @Inject
    public GetUserAdsActivity(AdDao adDao) {
        this.adDao = adDao;
    }

    public GetUserAdsResult handleRequest(final GetUserAdsRequest getUserAdsRequest) {
        log.info("Recieved GetUserAdsRequest {}", getUserAdsRequest);

        List<Ad> results = adDao.getUserAds(getUserAdsRequest.getUserId());
        List<AdModel> adModels = new ModelConverter().toAdModelList(results);

        return GetUserAdsResult.builder()
                .withAds(adModels)
                .build();
    }
}

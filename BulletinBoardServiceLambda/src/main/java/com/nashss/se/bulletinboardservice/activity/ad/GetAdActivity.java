package com.nashss.se.bulletinboardservice.activity.ad;

import com.nashss.se.bulletinboardservice.activity.requests.GetAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetAdResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.models.AdModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetAdActivity {
    private final Logger log = LogManager.getLogger();
    private final AdDao adDao;

    @Inject
    public GetAdActivity(AdDao adDao) {
        this.adDao = adDao;
    }

    public GetAdResult handleRequest(final GetAdRequest getAdRequest) {
        log.info("Received GetAdRequest {}", getAdRequest);

        Ad result = adDao.getAd(getAdRequest.getUserId(), getAdRequest.getAdId());
        AdModel adModel = new ModelConverter().toAdModel(result);

        return GetAdResult.builder()
                .withAd(adModel)
                .build();
    }
}

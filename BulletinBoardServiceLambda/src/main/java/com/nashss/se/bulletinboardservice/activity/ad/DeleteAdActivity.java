package com.nashss.se.bulletinboardservice.activity.ad;

import com.amazonaws.services.dynamodbv2.model.Delete;
import com.nashss.se.bulletinboardservice.activity.requests.DeleteAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.DeleteAdResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.models.AdModel;
import com.nashss.se.bulletinboardservice.utils.BullitenBoardServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import javax.inject.Inject;

public class DeleteAdActivity {
    private final Logger log = LogManager.getLogger();
    private final AdDao adDao;

    @Inject
    public DeleteAdActivity(AdDao adDao) {
        this.adDao = adDao;
    }

    public DeleteAdResult handleRequest(final DeleteAdRequest deleteAdRequest) {
        log.info("Received DeleteAdRequest {}", deleteAdRequest);

        Ad ad = adDao.getAd(deleteAdRequest.getUserId(), deleteAdRequest.getAdId());

        adDao.deleteAd(ad);

        return DeleteAdResult.builder()
                .withAd(new ModelConverter().toAdModel(ad))
                .build();
    }
}

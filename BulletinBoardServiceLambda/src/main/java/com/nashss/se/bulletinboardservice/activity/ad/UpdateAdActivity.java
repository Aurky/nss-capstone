package com.nashss.se.bulletinboardservice.activity.ad;

import com.nashss.se.bulletinboardservice.activity.requests.UpdateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.UpdateAdResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.metrics.MetricsPublisher;
import com.nashss.se.bulletinboardservice.models.AdModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateAdActivity {
    private final Logger log = LogManager.getLogger();
    private final AdDao adDao;

    @Inject
    public UpdateAdActivity(AdDao adDao) {
        this.adDao = adDao;
    }

    public UpdateAdResult handleRequest(final UpdateAdRequest updateAdRequest) {
        log.info("Received UpdateAdRequest {}", updateAdRequest);


        Ad ad = adDao.getAd(updateAdRequest.getUserId(), updateAdRequest.getAdId());

        ad.setName(updateAdRequest.getName());
        ad.setDescription(updateAdRequest.getDescription());
        ad.setSalary(updateAdRequest.getSalary());
        ad.setLocation(updateAdRequest.getLocation());
        ad.setVenue(updateAdRequest.getVenue());
        ad.setTags(updateAdRequest.getTags());
        ad = adDao.saveAd(ad);

        return UpdateAdResult.builder()
                .withAd(new ModelConverter().toAdModel(ad))
                .build();
    }
}

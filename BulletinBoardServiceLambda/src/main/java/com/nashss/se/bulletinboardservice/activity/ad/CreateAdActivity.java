package com.nashss.se.bulletinboardservice.activity.ad;

import com.nashss.se.bulletinboardservice.activity.requests.CreateAdRequest;
import com.nashss.se.bulletinboardservice.activity.results.CreateAdResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.AdDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Ad;
import com.nashss.se.bulletinboardservice.models.AdModel;
import com.nashss.se.bulletinboardservice.utils.BullitenBoardServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
public class CreateAdActivity {
    private final Logger log = LogManager.getLogger();
    private final AdDao adDao;

    /**
     * Instantiates a new CreateAdActivity object.
     *
     * @param adDao AdDao to access the ads table.
     */
    @Inject
    public CreateAdActivity(AdDao adDao) {
        this.adDao = adDao;
    }

    /**
     * This method handles the incoming request by persisting a new ad
     * with the provided name and ad ID from the request.
     * <p>
     * It then returns the newly created ad.
     * <p>
     * If the provided name or ad ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createAdRequest request object containing the name and ad ID
     *                              associated with it
     * @return createAdResult result object containing the API defined {@link AdModel}
     */
    public CreateAdResult handleRequest(final CreateAdRequest createAdRequest) {
        log.info("Received CreateAdRequest {}", createAdRequest);

        Set<String> tags = null;
        if (createAdRequest.getTags() != null) {
            tags = new HashSet<>(createAdRequest.getTags());
        }

        Ad newAd = new Ad();
        newAd.setAdId(BullitenBoardServiceUtils.generateAdId());
        newAd.setName(createAdRequest.getName());
        newAd.setDescription(createAdRequest.getDescription());
        newAd.setSalary(createAdRequest.getSalary());
        newAd.setLocation(createAdRequest.getLocation());
        newAd.setVenue(createAdRequest.getVenue());
        newAd.setUserId(createAdRequest.getUserId());
        newAd.setTags(tags);

        adDao.saveAd(newAd);

        AdModel adModel = new ModelConverter().toAdModel(newAd);
        return CreateAdResult.builder()
                .withAd(adModel)
                .build();
    }


}

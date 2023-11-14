package com.nashss.se.bulletinboardservice.activity.venue;
import com.nashss.se.bulletinboardservice.activity.requests.GetVenueRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetVenueResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.VenueDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Venue;
import com.nashss.se.bulletinboardservice.models.VenueModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetVenueActivity {
    private final Logger log = LogManager.getLogger();
    private final VenueDao venueDao;

    @Inject
    public GetVenueActivity(VenueDao venueDao) {
        this.venueDao = venueDao;
    }

    public GetVenueResult handleRequest(final GetVenueRequest getVenueRequest) {
        log.info("Received GetVenueRequest {}", getVenueRequest);

        Venue result = venueDao.getVenue(getVenueRequest.getVenueId());
        VenueModel venueModel = new ModelConverter().toVenueModel(result);

        return GetVenueResult.builder()
                .withVenue(venueModel)
                .build();
    }
}

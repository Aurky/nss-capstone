package com.nashss.se.bulletinboardservice.activity.location;
import com.nashss.se.bulletinboardservice.activity.requests.GetLocationRequest;
import com.nashss.se.bulletinboardservice.activity.results.GetLocationResult;
import com.nashss.se.bulletinboardservice.converters.ModelConverter;
import com.nashss.se.bulletinboardservice.dynamodb.LocationDao;
import com.nashss.se.bulletinboardservice.dynamodb.models.Location;
import com.nashss.se.bulletinboardservice.models.LocationModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetLocationActivity {
    private final Logger log = LogManager.getLogger();
    private final LocationDao locationDao;

    @Inject
    public GetLocationActivity(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public GetLocationResult handleRequest(final GetLocationRequest getLocationRequest) {
        log.info("Received GetLocationRequest {}", getLocationRequest);

        Location result = locationDao.getLocation(getLocationRequest.getLocationId());
        LocationModel locationModel = new ModelConverter().toLocationModel(result);

        return GetLocationResult.builder()
                .withLocation(locationModel)
                .build();
    }
}

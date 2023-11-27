package com.nashss.se.bulletinboardservice.activity.requests;

import com.nashss.se.bulletinboardservice.activity.requests.UpdateAdRequest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateAdRequestTest {

    @Test
    void testUpdateAdRequest() {

        String userId = "sampleUserId";
        String name = "Updated Ad";
        String adId = "sampleAdId";
        String description = "Updated description";
        String location = "Updated Location";
        String venue = "Updated Venue";
        Double salary = 1500.0;
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");

        UpdateAdRequest updateAdRequest = UpdateAdRequest.builder()
                .withUserId(userId)
                .withName(name)
                .withAdId(adId)
                .withDescription(description)
                .withLocation(location)
                .withVenue(venue)
                .withSalary(salary)
                .withTags(tags)
                .build();

        assertEquals(userId, updateAdRequest.getUserId());
        assertEquals(name, updateAdRequest.getName());
        assertEquals(adId, updateAdRequest.getAdId());
        assertEquals(description, updateAdRequest.getDescription());
        assertEquals(location, updateAdRequest.getLocation());
        assertEquals(venue, updateAdRequest.getVenue());
        assertEquals(salary, updateAdRequest.getSalary());
        assertEquals(tags, updateAdRequest.getTags());
    }
}
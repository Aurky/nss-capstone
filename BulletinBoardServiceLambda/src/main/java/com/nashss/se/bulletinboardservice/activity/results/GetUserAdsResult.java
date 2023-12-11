package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.AdModel;

import java.util.ArrayList;
import java.util.List;

public class GetUserAdsResult {
    private final List<AdModel> ads;

    private GetUserAdsResult(List<AdModel> ads) {
        this.ads = ads;
    }

    public List<AdModel> getAds() {
        return ads;
    }

    @Override
    public String toString() {
        return "GetUserAdsResult{" +
                "ads=" + ads +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<AdModel> ads;

        public Builder withAds(List<AdModel> ads) {
            this.ads = new ArrayList<>(ads);
            return this;
        }

        public GetUserAdsResult build() {
            return new GetUserAdsResult(ads);
        }
    }
}

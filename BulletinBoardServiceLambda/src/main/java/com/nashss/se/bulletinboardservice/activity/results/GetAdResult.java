package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.AdModel;

public class GetAdResult {
    private final AdModel ad;

    private GetAdResult(AdModel ad) {
        this.ad = ad;
    }

    public AdModel getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return "GetAdResult{" +
                "ad=" + ad +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AdModel ad ;

        public Builder withAd(AdModel ad) {
            this.ad = ad;
            return this;
        }

        public GetAdResult build() {
            return new GetAdResult(ad);
        }
    }
}

package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.AdModel;


public class CreateAdResult {

    private final AdModel ad;

    private CreateAdResult(AdModel ad) {
        this.ad = ad;
    }

    public AdModel getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return "CreateAdResult{" +
                "ad=" + ad +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AdModel ad;

        public Builder withAd(AdModel ad) {
            this.ad = ad;
            return this;
        }

        public CreateAdResult build() {
            return new CreateAdResult(ad);
        }
    }
}

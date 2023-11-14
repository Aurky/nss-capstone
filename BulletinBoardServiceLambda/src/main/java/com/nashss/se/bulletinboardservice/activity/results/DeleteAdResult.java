package com.nashss.se.bulletinboardservice.activity.results;

import com.nashss.se.bulletinboardservice.models.AdModel;

public class DeleteAdResult {
    private final AdModel ad;

    private DeleteAdResult(AdModel ad) {
        this.ad = ad;
    }

    public AdModel getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return "DeleteAdResult{" +
                "ad=" + ad +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DeleteAdResult.Builder builder() {
        return new DeleteAdResult.Builder();
    }

    public static class Builder {
        private AdModel ad;

        public DeleteAdResult.Builder withAd(AdModel ad) {
            this.ad = ad;
            return this;
        }

        public DeleteAdResult build() {
            return new DeleteAdResult(ad);
        }
    }
}

package com.nashss.se.bulletinboardservice.activity.requests;

public class GetAdRequest {
    private final String adId;
    private final String userId;

    private GetAdRequest(String userId, String adId) {
        this.userId = userId;
        this.adId = adId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAdId() {
        return adId;
    }

    @Override
    public String toString() {
        return "GetAdRequest{" +
                "userId='" + userId + '\'' +
                "adId='" + adId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String adId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withAdId(String adId) {
            this.adId = adId;
            return this;
        }

        public GetAdRequest build() {
            return new GetAdRequest(userId, adId);
        }
    }
}

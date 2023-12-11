package com.nashss.se.bulletinboardservice.activity.requests;

public class GetUserAdsRequest {
    private final String userId;

    private GetUserAdsRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetUserAdsRequest{" +
                "userId=" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetUserAdsRequest build() {
            return new GetUserAdsRequest(userId);
        }
    }
}

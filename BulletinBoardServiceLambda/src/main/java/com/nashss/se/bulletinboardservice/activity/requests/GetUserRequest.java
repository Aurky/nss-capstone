package com.nashss.se.bulletinboardservice.activity.requests;

public class GetUserRequest {
    private final String userId;

    private GetUserRequest(String uesrId) {
        this.userId = uesrId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "userId='" + userId + '}';
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

        public GetUserRequest build() {
            return new GetUserRequest(userId);
        }
    }
}

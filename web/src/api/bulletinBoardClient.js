import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

export default class BulletinBoardClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createUser', 'getUser', 'updateUserDetails', 'createAd', 'updateAdDetails', 'getAd'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    async createUser(name, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a user.");
            const response = await this.axiosClient.post(`users`, {
            name: name
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            return response.data.user;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getUser(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get users.");
            const response = await this.axiosClient.get(`users/${userId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.user;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async updateUserDetails(userId, newUserName, newUserBio, newUserGroups, newUserRoles, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can change user details.");
            const response = await this.axiosClient.put(`users/${userId}`, {
                userId: userId,
                name: newUserName,
                bio: newUserBio,
                groups: newUserGroups,
                roles: newUserRoles,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.user;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async createAd(adName, adDescription, adLocation, adTags, adSalary, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create ads.");
            const response = await this.axiosClient.post(`ads`, {
                name: adName,
                description: adDescription,
                location: adLocation,
                tags: adTags,
                salary: adSalary
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.ad;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getAd(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get ads.");
            const response = await this.axiosClient.get(`ad/${adId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.user;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async updateAdDetails(adId, newAdName, newAdDescription, newAdLocation, newAdTags, newAdSalary, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can change ad details.");
            const response = await this.axiosClient.put(`ads/${adId}`, {
                adId: adId,
                name: newAdName,
                description: newAdDescription,
                location: newAdLocation,
                tags: newAdTags,
                salary: newAdSalary,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.user;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        /**
         * Search for a song.
         * @param criteria A string containing search criteria to pass to the API.
         * @returns The playlists that match the search criteria.
         */
        async search(criteria, errorCallback) {
            try {
                const queryParams = new URLSearchParams({ q: criteria })
                const queryString = queryParams.toString();

                const response = await this.axiosClient.get(`users/search?${queryString}`);

                return response.data.users;
            } catch (error) {
                this.handleError(error, errorCallback)
            }

        }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }




}
import BulletinBoardClient from '../api/bulletinBoardClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from "../api/authenticator";


const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class UserSetting extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'submitChanges', 'clientLoaded'], this);
        this.authenticator = new Authenticator();
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);

        console.log("userSetting constructor");
    }

    async clientLoaded() {

        const userId = await this.authenticator.getCurrentUserEmail();
        const user = await this.client.getUser(userId);
        console.log(user);

        document.getElementById('user-name').innerText = user.name;
        document.getElementById('user-bio').innerText = user.bio;
        document.getElementById('user-groups').innerText = user.groups;
        document.getElementById('user-roles').innerText = user.roles;

    }

    mount() {

        document.getElementById('save-changes-btn').addEventListener('click', this.submitChanges);

        this.header.addHeaderToPage();
        this.client = new BulletinBoardClient();
        this.clientLoaded()

    }

    async submitChanges(evt) {
        evt.preventDefault();

//        const user = this.dataStore.get('user');
//        if (user == null) {
//            return;
//        }
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('save-changes-btn');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Updating...';

        const userId = await this.authenticator.getCurrentUserEmail();
        const user = await this.client.getUser(userId);

        const newUserName = document.getElementById('user-name-field').value;
        const newUserBio = document.getElementById('user-bio-field').value;
        const newUserGroups = document.getElementById('user-groups-field').value;
        const newUserRoles = document.getElementById('user-roles-field').value;

        let groups;
        if (newUserGroups.length < 1) {
            groups = null;
        } else {
            groups = newUserGroups.split(/\s*,\s*/);
        }

        let roles;
        if (newUserRoles.length < 1) {
            roles = null;
        } else {
            roles = newUserRoles.split(/\s*,\s*/);
        }

        const updatedUser = await this.client.updateUserDetails(userId, newUserName, newUserBio, groups, roles, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('user', updatedUser);
    }
}



const main = async () => {
    const userSetting = new UserSetting();
    userSetting.mount();
};

window.addEventListener('DOMContentLoaded', main);
import BulletinBoardClient from '../api/bulletinBoardClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";



const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class UserSetting extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'submitChanges', 'addUserNameToPage', 'addUserBioToPage', 'addUserGroupsToPage', 'addUserRolesToPage'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.addUserNameToPage);
        this.dataStore.addChangeListener(this.addUserBioToPage);
        this.dataStore.addChangeListener(this.addUserGroupsToPage);
        this.dataStore.addChangeListener(this.addUserRolesToPage);
        console.log("userSetting constructor");
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const userId = urlParams.get('id');
        document.getElementById('user-name').innerText = "Loading User Details...";
        const user = await this.client.getUser(userId);
        this.dataStore.set('user', user);
    }

    mount() {

        document.getElementById('save-changes-btn').addEventListener('click', this.submitChanges);

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

    }

    addUserNameToPage() {
        const user = this.dataStore.get('user');
        if (user == null) {
            return;
        }
        document.getElementById('user-name').innerText = user.name;
    }

    addUserBioToPage() {
        const user = this.dataStore.get('user');
        if (user == null) {
            return;
        }
        document.getElementById('user-bio').innerText = user.bio;
    }

    addUserGroupsToPage() {
        const user = this.dataStore.get('user');
        if (user == null) {
            return;
        }
        document.getElementById('user-groups').innerText = user.groups;
    }

    addUserRolesToPage() {
        const user = this.dataStore.get('user');
        if (user == null) {
            return;
        }
        document.getElementById('user-roles').innerText = user.roles;
    }

    async submitChanges() {

        const user = this.dataStore.get('user');
        if (user == null) {
            return;
        }

        const userId = user.userId;

        const newUserName = document.getElementById('user-name-field').value;
        const newUserBio = document.getElementById('user-bio-field').value;
        const newUserGroups = document.getElementById('user-groups-field').value;
        const newUserRoles = document.getElementById('user-roles-field').value;

        const errorMessageDisplay = document.getElementById('error-message');
                errorMessageDisplay.innerText = ``;
                errorMessageDisplay.classList.add('hidden');

        const updatedUser = await this.client.updateUserDetails(userId, newUserName, newUserBio, newUserGroups, newUserRoles, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        document.getElementById('save-changes-btn').innerText = 'Submitting Changes...';
        location.reload();
    }
}



const main = async () => {
    const userSetting = new UserSetting();
    userSetting.mount();
};

window.addEventListener('DOMContentLoaded', main);
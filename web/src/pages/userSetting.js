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

        this.bindClassMethods(['mount'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        console.log("userSetting constructor");
    }

    mount() {

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

    }
}



const main = async () => {
    const userSetting = new UserSetting();
    userSetting.mount();
};

window.addEventListener('DOMContentLoaded', main);
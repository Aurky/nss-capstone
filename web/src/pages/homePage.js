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

class HomePage extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'clientLoaded'], this);

        this.authenticator = new Authenticator();
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        console.log("homePage constructor");
    }

    mount() {

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

        this.clientLoaded();

    }

    async clientLoaded() {
        const userId = await this.authenticator.getCurrentUserEmail();
        const user = await this.client.getUser(userId);
        console.log(user);
    }
}



const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
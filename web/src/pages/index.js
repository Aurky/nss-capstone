import BulletinBoardClient from '../api/bulletinBoardClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from "../api/authenticator";


/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class Index extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'submit'], this);
        this.auth = new Authenticator();
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);

        console.log("index constructor");
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {

//        document.getElementById('search-playlists-form').addEventListener('submit', this.search);
        document.getElementById('home-page-btn').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

    }

    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById("error-message");
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

//        const homePageButton = document.getElementById('home-page-button');
//        const origButtonText = homePageButton.innerText;
//        homePageButton.innerText = 'Loading...';

        const name = await this.auth.getCurrentUserName;

        const user = await this.client.createUser(name, (error) => {
//            homePageButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('user', user);
    }
}



const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);
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

class CreateAd extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'submit', 'redirectToViewAd'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.redirectToViewAd);
    }

    mount() {

        document.getElementById('create-ad-btn').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

    }

    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create-ad-btn');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Creating...';

        const adName = document.getElementById('ad-name').value;
        const adDescription = document.getElementById('ad-description').value;
        const adLocation = document.getElementById('ad-location').value;
        const adTags = document.getElementById('ad-tags').value;
        const adSalary = document.getElementById('ad-salary').value;

        const ad = await this.client.createAd(adName, adDescription, adLocation, adTags, adSalary, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('ad', ad);
    }

    redirectToViewAd() {
        const ad = this.dataStore.get('ad');

        if (ad != null) {
            window.location.href = '/viewAd.html?id=${ad.adId}';
        }
    }
}



const main = async () => {
    const createAd = new CreateAd();
    createAd.mount();
};

window.addEventListener('DOMContentLoaded', main);
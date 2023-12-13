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

class UpdateAd extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'submitChanges', 'redirectToViewAd', 'clientLoaded'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.redirectToViewAd);
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');
        document.getElementById('ad-name').innerText = "Loading Ad Details...";
        const ad = await this.client.getAd(adId);
        document.getElementById('ad-name').innerText = "Input Updates:";
        document.getElementById('ad-name-field').value = ad.name;
        document.getElementById('ad-description-field').value = ad.description;
        document.getElementById('ad-location-field').value = ad.location;
        document.getElementById('ad-venue-field').value = ad.venue;
        document.getElementById('ad-tags-field').value = ad.tags;
        document.getElementById('ad-salary-field').value = ad.salary;
    }

    mount() {

        document.getElementById('save-changes-btn').addEventListener('click', this.submitChanges);

        this.header.addHeaderToPage();
        this.client = new BulletinBoardClient();
        this.clientLoaded();

    }

    async submitChanges(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('save-changes-btn');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Updating...';

        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');

        const newAdName = document.getElementById('ad-name-field').value;
        const newAdDescription = document.getElementById('ad-description-field').value;
        const newAdLocation = document.getElementById('ad-location-field').value;
        const newAdVenue = document.getElementById('ad-venue-field').value;
        const newAdTags = document.getElementById('ad-tags-field').value;
        const newAdSalary = document.getElementById('ad-salary-field').value;

        let tags;
        if (newAdTags.length < 1) {
            tags = null;
        } else {
            tags = newAdTags.split(/\s*,\s*/);
        }

        const updatedAd = await this.client.updateAdDetails(adId, newAdName, newAdDescription, newAdLocation, newAdVenue, tags, newAdSalary, (error) => {
            updateButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('ad', updatedAd);
        window.location.href = `/viewAd.html?id=${adId}`;


    }

    redirectToViewAd() {
        const ad = this.dataStore.get('ad');
        if (ad != null) {
            window.location.href = `/viewAd.html?id=${ad.adId}`;
        }
    }
}



const main = async () => {
    const updateAd = new UpdateAd();
    updateAd.mount();
};

window.addEventListener('DOMContentLoaded', main);
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

class ViewAd extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'clientLoaded', 'deleteAd', 'redirectToUpdateAd', 'updateAd'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.redirectToUpdateAd);
        this.dataStore.addChangeListener(this.deleteAd);

    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');
        document.getElementById('ad-name').innerText = "Loading Ad...";
        const ad = await this.client.getAd(adId);
        document.getElementById('ad-name').innerText = ad.name;
        document.getElementById('ad-description').innerText = ad.description;
        document.getElementById('ad-location').innerText = ad.location;
        document.getElementById('ad-venue').innerText = ad.venue;
        document.getElementById('ad-tags').innerText = ad.tags;
        document.getElementById('ad-salary').innerText = ad.salary;
    }

    mount() {

        document.getElementById('delete-ad-btn').addEventListener('click', this.deleteAd);
        document.getElementById('update-ad-btn').addEventListener('click', this.updateAd);

        this.header.addHeaderToPage();
        this.client = new BulletinBoardClient();
        this.clientLoaded();

    }

    async deleteAd(evt) {
        evt.preventDefault();

        const confirmation = window.confirm("Are you sure you want to delete this ad?");
        if (!confirmation) {
            return;
        }

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const deleteButton = document.getElementById('delete-ad-btn');
        const origButtonText = deleteButton.innerText;
        deleteButton.innerText = 'Deleting...';

        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');

        await this.client.deleteAd(adId, (error) => {
            deleteButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        window.location.href = `/homePage.html`;

    }

    async updateAd(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('update-ad-btn');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Redirecting...';

        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');
        const ad = await this.client.getAd(adId);

        this.dataStore.set('ad', ad);



    }

    redirectToUpdateAd() {
        const ad = this.dataStore.get('ad');
        if (ad != null) {
            window.location.href = `/updateAd.html?id=${ad.adId}`;
        }
    }
}

const main = async () => {
    const viewAd = new ViewAd();
    viewAd.mount();
};

window.addEventListener('DOMContentLoaded', main);
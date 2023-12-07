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

        this.bindClassMethods(['mount', 'submitChanges', 'addAdNameToPage', 'addAdDescriptionToPage', 'addAdLocationToPage', 'addAdTagsToPage', 'addAdSalaryToPage', 'redirectToViewAd', 'clientLoaded'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.addAdNameToPage);
        this.dataStore.addChangeListener(this.addAdDescriptionToPage);
        this.dataStore.addChangeListener(this.addAdLocationToPage);
        this.dataStore.addChangeListener(this.addAdTagsToPage);
        this.dataStore.addChangeListener(this.addAdSalaryToPage);
        this.dataStore.addChangeListener(this.redirectToViewAd);
        console.log("updateAd constructor");
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');
        document.getElementById('ad-name').innerText = "Loading Ad Details...";
        const user = await this.client.getAd(adId);
        this.dataStore.set('ad', ad);
    }

    mount() {

        document.getElementById('save-changes-btn').addEventListener('click', this.submitChanges);

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

    }

    addAdNameToPage() {
            const user = this.dataStore.get('ad');
            if (user == null) {
                return;
            }
            document.getElementById('ad-name').innerText = ad.name;
        }

    addAdDescriptionToPage() {
            const user = this.dataStore.get('ad');
            if (user == null) {
                return;
            }
            document.getElementById('ad-description').innerText = ad.description;
        }

    addAdLocationToPage() {
            const user = this.dataStore.get('ad');
            if (user == null) {
                return;
            }
            document.getElementById('ad-location').innerText = ad.location;
        }

    addAdTagsToPage() {
            const user = this.dataStore.get('ad');
            if (user == null) {
                return;
            }
            document.getElementById('ad-tags').innerText = ad.tags;
        }

    addAdSalaryToPage() {
            const user = this.dataStore.get('ad');
            if (user == null) {
                return;
            }
            document.getElementById('ad-salary').innerText = ad.salary;
        }


    async submitChanges(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const ad = this.dataStore.get('ad');
        if (ad == null) {
            return;
        }

        const adId = ad.adId;

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

        const updatedAd = await this.client.updateAdDetails(adId, newAdName, newAdDescription, newAdLocation, newAdTags, newAdSalary, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('ad', ad);

        document.getElementById('save-changes-btn').innerText = 'Submitting Changes...';
        location.reload();
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
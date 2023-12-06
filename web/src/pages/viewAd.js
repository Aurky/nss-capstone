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

        this.bindClassMethods(['mount', 'addAdToPage', 'clientLoaded'], this);

        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.addAdToPage);
        console.log("viewAd constructor");
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('ad');
        document.getElementById('ad-name').innerText = "Loading Ad...";
        const ad = await this.client.getAd(adId);
        this.datastore.set('ad', ad);

    }

    mount() {

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

    }

    addAdToPage() {
        const ad = this.datastore.get('ad');
        if (ad == null) {
            return;
        }

        document.getElementById('ad-name').innerText = ad.name;
        document.getElementById('ad-owner').innerText = ad.userId;
        document.getElementById('location').innerText = ad.location;
    }
}



const main = async () => {
    const viewAd = new ViewAd();
    viewAd.mount();
};

window.addEventListener('DOMContentLoaded', main);
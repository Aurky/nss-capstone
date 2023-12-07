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

        this.bindClassMethods(['mount', 'addAdNameToPage', 'addAdDescriptionToPage', 'clientLoaded'], this);

        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addAdNameToPage);
        this.dataStore.addChangeListener(this.addAdDescriptionToPage);
        this.header = new Header(this.dataStore);

        console.log("viewAd constructor");
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const adId = urlParams.get('id');
        console.log(adId);
        document.getElementById('ad-name').innerText = "Loading Ad...";
        document.getElementById('ad-owner').innerText = "Loading Owner...";
        console.log(adId);
        const ad = await this.client.getAd(adId);
        console.log(ad);
        this.datastore.set('ad', ad);

    }

    mount() {

        this.header.addHeaderToPage();
        this.client = new BulletinBoardClient();
        this.clientLoaded();

    }

    addAdNameToPage() {
        const ad = this.datastore.get('ad');
        if (ad == null) {
            return;
        }
        document.getElementById('ad-name').innerText = ad.name;

    }

    addAdDescriptionToPage() {
            const ad = this.datastore.get('ad');
            if (ad == null) {
                return;
            }
            document.getElementById('ad-description').innerText = ad.description;
        }


}



const main = async () => {
    const viewAd = new ViewAd();
    viewAd.mount();
};

window.addEventListener('DOMContentLoaded', main);
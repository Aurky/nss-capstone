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

        this.bindClassMethods(['mount', 'clientLoaded', 'addAdsToPage', 'redirectToViewAd', 'submit'], this);

        this.authenticator = new Authenticator();
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.redirectToViewAd);
        this.dataStore.addChangeListener(this.addAdsToPage)
    }

    mount() {

        document.getElementById('adsSelect').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new BulletinBoardClient();

        this.clientLoaded();

    }

    async clientLoaded() {
        const userId = await this.authenticator.getCurrentUserEmail();
        const user = await this.client.getUser(userId);
        console.log(user);
        const ads = await this.client.getAdList();
        console.log(ads);
        this.dataStore.set('ads', ads);
//        document.getElementById('ad-list').innerText = ads;
        document.getElementById('user-name').innerText = user.name;
        document.getElementById('user-bio').innerText = user.bio;
        document.getElementById('user-groups').innerText = user.groups;
        document.getElementById('user-roles').innerText = user.roles;
    }

    addAdsToPage() {
        const ads = this.dataStore.get('ads');
        console.log(ads);
        if (ads == null) {
            return;
        }

        document.getElementById('adsSelect').size = ads.length;
        let optionList = document.getElementById('adsSelect').options;
        let options = [
        {
            text: 'Option 1',
            value: 'Value 1'
        },
        {
            text: 'Option 2',
            value: 'Value 2'
        },
        {
            text: 'Option 3',
            value: 'Value 3'
        },
        {
            text: 'Option 4',
            value: 'Value 4'
        },
        {
            text: 'Option 5',
            value: 'Value 5'
        },
        {
            text: 'Option 6',
            value: 'Value 6'
        }
        ];

        ads.forEach(ads =>
            optionList.add(
            new Option(ads.name, ads.adId)
        ));
    }

    redirectToViewAd() {
        const adId = this.dataStore.get('adId');
        console.log(adId);
        if (adId != null) {
            window.location.href = `/viewAd.html?id=${adId}`;
        }
    }

    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const adId = document.getElementById('adsSelect').value;
        console.log("selected ads' id: " + adId);

        this.dataStore.set('adId', adId);
    }
}



const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
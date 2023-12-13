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

//        document.getElementById('adsSelect').addEventListener('click', this.submit);

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
//        document.getElementById('user-name').innerText = "Name: " + user.name;
//        console.log(user.name);
//        document.getElementById('user-bio').innerText = "Bio: " + user.bio;
//        console.log(user.bio);
//        document.getElementById('user-groups').innerText = user.groups;
//        console.log(user.groups);
//        document.getElementById('user-roles').innerText = user.roles;
//        console.log(user.roles);
    }

    addAdsToPage() {
        const adList = this.dataStore.get('ads');
        const adListElement = document.getElementById('adList');

        if (adList == null || adList.length === 0) {
            const messageHtml = '<p>You do not have any ads yet!</p>';
            adListElement.innerHTML = messageHtml;
            return;
        }

        const textHtml = '<h6>Click an Ad below to view:</h6>';

        let adHtml = '<table border="1"><tr><th>Ad ID</th><th>Name</th></tr>';

        for (const ad of adList) {
            let adId = ad.adId;
            let name = ad.name;

            adHtml += `
                <tr class="ad-row" onclick="redirectToViewAd('${adId}')">
                    <td>${adId}</td>
                    <td>${name}</td>
                </tr>
            `;
        }

        adHtml += '</table>';

        adListElement.innerHTML = textHtml + adHtml;
    }

//    addAdsToPage() {
////        const ads = this.dataStore.get('ads');
////        console.log(ads);
////        if (ads == null) {
////            return;
////        }
//
//
////        document.getElementById('adsSelect').size = ads.length;
//
//        const adListElement = document.getElementById('adList');
//
//        if (adList == null || adList.length ===0) {
//            const messageHtml = '<p>You do not have any ads yet!>';
//            adListElement.innerHTML = messageHtml;
//            return;
//        }
//
//        const adListMap = {};
//
//
//        const textHtml = '<h6>Click an Ad below to view</h6>';
//
//        let adHtml = '<table><tr><th>Ad</th>';
//                for (const ad of adList) {
//                    let adId = ad.adId;
//                    let name = ad.name;
//
//                    adHtml += `
//                    <tr onclick="window.location='/viewAd.html?id=${ad.adId}'">
//                        <td>ad.name</td>
//                    </tr>
//                    `;
//        }
//
//        adHtml += '</table';
//
//        adListElement.innerHTML = textHtml + adHtml;
//
//
//    }

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
import axios from "axios";

const CUSTOMERLOANS_REST_API_URL = 'http://localhost:8088/ezloans/api/allLoans';
const CUSTOMERITEMS_REST_API_URL = 'http://localhost:8088/ezloans/api/allItems';

const LOANS_REST_API_URL = 'http://localhost:8088/ezloans/api/loans';
const ITEMS_REST_API_URL = 'http://localhost:8088/ezloans/api/items';

const CUSTOMER_PURCHASE_ITEM_REST_API_URL = 'http://localhost:8088/ezloans/api/purchaseItem';

class UserService {

    static getCustomerLoans(json_credentials, userId) {
        var credentials = JSON.parse(json_credentials);
        return axios.get(CUSTOMERLOANS_REST_API_URL + '/' + userId, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static getLoanDetails(json_credentials, loanId) {
        var credentials = JSON.parse(json_credentials);
        return axios.get(LOANS_REST_API_URL + '/' + loanId, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static getCustomerItems(json_credentials, userId) {
        var credentials = JSON.parse(json_credentials);
        return axios.get(CUSTOMERITEMS_REST_API_URL + '/' + userId, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static getItemDetails(json_credentials, itemId) {
        var credentials = JSON.parse(json_credentials);
        return axios.get(ITEMS_REST_API_URL + '/' + itemId, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static purchaseItem(itemdata, json_credentials, userId) {
        var credentials = JSON.parse(json_credentials);
        var date = (new Date()).toISOString().split('T')[0];
        console.log(date);
        console.log("yes123");
        console.log(credentials);
        var purchasedata = {
            "emp": {
                "employeeId": userId
            },
            "item": {
                "itemId": itemdata.itemId
            },
            "purchaseDate": date
        }
        return axios.post(CUSTOMER_PURCHASE_ITEM_REST_API_URL, purchasedata, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }
}

export default UserService;
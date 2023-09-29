import axios from "axios";

const CUSTOMERLOANS_REST_API_URL = 'http://localhost:8088/ezloans/api/allLoans';
const CUSTOMERITEMS_REST_API_URL = 'http://localhost:8088/ezloans/api/allItems';
// const CUSTOMER_PURCHASE_ITEM_REST_API_URL = 'http://localhost:8088/ezloans/api/users';
const LOANS_REST_API_URL = 'http://localhost:8088/ezloans/api/loans';
const ITEMS_REST_API_URL = 'http://localhost:8088/ezloans/api/items';



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


}

export default UserService;
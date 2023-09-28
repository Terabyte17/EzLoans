import axios from "axios";

const CUSTOMERDATA_REST_API_URL = 'http://localhost:8088/ezloans/api/users';
const CUSTOMERDATA_UPDATE_REST_API_URL = 'http://localhost:8088/ezloans/api/users/update'
const CUSTOMERDATA_DELETE_REST_API_URL = 'http://localhost:8088/ezloans/api/users/delete'

const LOANDATA_REST_API_URL = 'http://localhost:8088/ezloans/api/loans';
const LOANDATA_UPDATE_REST_API_URL = 'http://localhost:8088/ezloans/api/loans/update';
const LOANDATA_DELETE_REST_API_URL = 'http://localhost:8088/ezloans/api/loans/delete';

const ITEMDATA_REST_API_URL = 'http://localhost:8088/ezloans/api/items';
const ITEMDATA_UPDATE_REST_API_URL = 'http://localhost:8088/ezloans/api/items/update';
const ITEMDATA_DELETE_REST_API_URL = 'http://localhost:8088/ezloans/api/items/delete';

class AdminService {

    static getCustomerData(json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.get(CUSTOMERDATA_REST_API_URL, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static createCustomerData(customer, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(CUSTOMERDATA_REST_API_URL, customer, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static updateCustomerData(customer, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(CUSTOMERDATA_UPDATE_REST_API_URL, customer, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static deleteCustomerData(customer, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(CUSTOMERDATA_DELETE_REST_API_URL, customer, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static getLoanCard(json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.get(LOANDATA_REST_API_URL, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static createLoanCard(loancard, json_credentials) {
        console.log(json_credentials);
        var credentials = JSON.parse(json_credentials);
        return axios.post(LOANDATA_REST_API_URL, loancard, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static updateLoanCard(loancard, json_credentials) {
        console.log("yay");
        console.log(json_credentials);
        var credentials = JSON.parse(json_credentials);
        return axios.post(LOANDATA_UPDATE_REST_API_URL, loancard, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static deleteLoanCard(loancard, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(LOANDATA_DELETE_REST_API_URL, loancard, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static getItemData(json_credentials) {
        var credentials = JSON.parse(json_credentials);
        // console.log(credentials);
        return axios.get(ITEMDATA_REST_API_URL, {
            auth: {
                "username": credentials.username,
                "password": credentials.password
            }
        });
    }

    static createItem(item, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(ITEMDATA_REST_API_URL, item, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static updateItem(item, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(ITEMDATA_UPDATE_REST_API_URL, item, {
            headers: {
                'Content-Type': 'application/json'
            },
            auth: {
                username: credentials.username,
                password: credentials.password
            }
        });
    }

    static deleteItem(item, json_credentials) {
        var credentials = JSON.parse(json_credentials);
        return axios.post(ITEMDATA_DELETE_REST_API_URL, item, {
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

export default AdminService;
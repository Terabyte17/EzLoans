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

    static getCustomerData() {
        return axios.get(CUSTOMERDATA_REST_API_URL);
    }

    static createCustomerData(customer) {
        return axios.post(CUSTOMERDATA_REST_API_URL, customer, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static updateCustomerData(customer) {
        return axios.post(CUSTOMERDATA_UPDATE_REST_API_URL, customer, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static deleteCustomerData(customer) {
        return axios.post(CUSTOMERDATA_DELETE_REST_API_URL, customer, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static getLoanCard() {
        return axios.get(LOANDATA_REST_API_URL);
    }

    static createLoanCard(loancard) {
        return axios.post(LOANDATA_REST_API_URL, loancard, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static updateLoanCard(loancard) {
        return axios.post(LOANDATA_UPDATE_REST_API_URL, loancard, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static deleteLoanCard(loancard) {
        return axios.post(LOANDATA_DELETE_REST_API_URL, loancard, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static getItemData() {
        return axios.get(ITEMDATA_REST_API_URL);
    }

    static createItem(item) {
        return axios.post(ITEMDATA_REST_API_URL, item, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static updateItem(item) {
        return axios.post(ITEMDATA_UPDATE_REST_API_URL, item, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    static deleteItem(item) {
        return axios.post(ITEMDATA_DELETE_REST_API_URL, item, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }
}

export default AdminService;
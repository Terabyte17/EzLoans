import axios from "axios";

const CUSTOMERDATA_REST_API_URL='http://localhost:8088/ezloans/api/users';
const LOANDATA_REST_API_URL = 'http://localhost:8088/ezloans/api/loans';
const ITEMDATA_REST_API_URL = 'http://localhost:8088/ezloans/api/items';

class AdminService{
    static getCustomerData(){
        return axios.get(CUSTOMERDATA_REST_API_URL);
    }

    static getLoanCard(){
        return axios.get(LOANDATA_REST_API_URL);
    }

    static getItemData(){
        return axios.get(ITEMDATA_REST_API_URL);
    }

    /*static createProduct(product){
        return axios.post(PRODUCTS_REST_API_URL,product);
    }

    static getProductById(){
        return axios.get(PRODUCTS_REST_API_URL+'/'+productId);
    }
    */

    static createCustomerData(customer){
        return axios.post(CUSTOMERDATA_REST_API_URL,customer);
    }

    static updateCustomerData(customer, customerId){
        return axios.post(CUSTOMERDATA_REST_API_URL+'/'+customerId,customer);
    }
    /*
    
    static deleteProduct(productId){
        return axios.delete(PRODUCTS_REST_API_URL+'/'+productId,product);
    } */
}

export default AdminService;
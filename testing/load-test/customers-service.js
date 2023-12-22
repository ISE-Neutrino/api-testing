import http from "k6/http";
import { check, sleep } from "k6";

const REQUEST_TIMEOUT = "180s"; // 3 minutes

/**
 * Test case configuration
 */
export const options = {
    tags: {
        test: 'customers-service',
        test_run_id: `Customers-Service-Performance-${__ENV.K6_ENV}`,
    },
    thresholds: {
        // addCustomer thresholds
        'http_req_failed{test_type:addCustomer}': ['rate<0.01'], // http errors should be less than 1% > System availability
        'http_req_duration{test_type:addCustomer}': ['p(95)<200'], // 95% of requests should be below 400ms > System latency

        // updateCustomer thresholds
        'http_req_failed{test_type:updateCustomer}': ['rate<0.01'],
        'http_req_duration{test_type:updateCustomer}': ['p(95)<200'],

        // listCustomers thresholds
        'http_req_failed{test_type:listCustomers}': ['rate<0.01'],
        'http_req_duration{test_type:listCustomers}': ['p(95)<200'],
    },
    scenarios: {

        // Load testing using K6 constant-rate scenario
        addCustomer_constant: {
            executor: 'constant-arrival-rate',
            rate: 10000,
            timeUnit: '1m', // iterations will be per minute
            duration: '1m', // total duration of test
            preAllocatedVUs: 2, // the size of the VU (i.e. worker) pool for this scenario > represents crs and retail-bank instances
            maxVUs: 50, // if the preAllocatedVUs are not enough, we can initialize more
            tags: { test_type: 'addCustomer' }, // different extra metric tags for this scenario
            exec: 'addCustomer',// Test scenario function to call
        },
        updateCustomer_constant: {
          executor: 'constant-arrival-rate',
          rate: 1000,
          timeUnit: '1m', // iterations will be per minute
          duration: '1m', // total duration of test
          preAllocatedVUs: 2, // the size of the VU (i.e. worker) pool for this scenario > represents crs and retail-bank instances
          maxVUs: 50, // if the preAllocatedVUs are not enough, we can initialize more
          tags: { test_type: 'updateCustomer' }, // different extra metric tags for this scenario
          exec: 'updateCustomer', // Test scenario function to call
        },
        listCustomers_constant: {
            executor: 'constant-arrival-rate',
            rate: 10000,
            timeUnit: '1m', // 90 iterations per minute, i.e. 1.5 RPS
            duration: '1m', // total duration of test
            preAllocatedVUs: 2, // the size of the VU (i.e. worker) pool for this scenario> represents the core-bank instances
            maxVUs: 50, // if the preAllocatedVUs are not enough, we can initialize more
            tags: { test_type: 'listCustomers' }, // different extra metric tags for this scenario
            exec: 'listCustomers', // Test scenario function to call
        }
    }
};
//----------------------------------------------------------------------------------------------------
/**
 * prepare the test data like authentication
 * @returns Initial data for each test case
 */
export function setup() {

    return { access_token: "access_token" };
}
//----------------------------------------------------------------------------------------------------
/**
 * Add customer test case
 */
export function addCustomer(data) {

    const url = __ENV.CUSTOMERS_API_URL + "/";
    const headers = {
        'Authorization': `Bearer ${data.access_token}`,
        'Content-Type': 'application/json'
    };

    const payload = `{
        "name": "Test Customer",
        "email": "email@test.com"
    }`;

    var response = http.post(url, payload, { timeout: REQUEST_TIMEOUT, headers: headers });

    check(response, { 'status is 200': (r) => r.status === 200 });
    if (response.status != 200) {
        console.log(`operation: addCustomer, url: ${url}, Status:${response.status}`);
    }
}
//----------------------------------------------------------------------------------------------------
/**
 * Update customer test case
 * @param {*} data 
 */
export function updateCustomer(data) {

    const customer_id = "c9cd67b8-7738-4736-b8c6-f35bb0154d09";

    const url = __ENV.CUSTOMERS_API_URL + `/${customer_id}`;

    const headers = {
        'Authorization': `Bearer ${data.access_token}`,
        'Content-Type': 'application/json'
    };

    const payload = `{
        "name": "Test Customer - Updated",
        "email": "email@test.com"
    }`;

    var response = http.patch(url, payload, { timeout: REQUEST_TIMEOUT, headers: headers });

    check(response, { 'status is 200': (r) => r.status === 200 });
    if (response.status != 200) {
        console.log(`operation: updateCustomer, url: ${url}, Status:${response.status}`);
    }
}
//----------------------------------------------------------------------------------------------------
/**
 * List customers test case
 * @param {*} data initial test data
 */
export function listCustomers(data) {

    const url = __ENV.CUSTOMERS_API_URL + "/";

    const headers = {
        'Authorization': `Bearer ${data.access_token}`,
        'Content-Type': 'application/json'
    };

    const payload = ``;

    var response = http.get(url, payload, { timeout: REQUEST_TIMEOUT, headers: headers });

    check(response, { 'status is 200': (r) => r.status === 200 });
    if (response.status != 200) {
        console.log(`operations: listCustomers, url: ${url}, Status:${response.status}`);
    }
}
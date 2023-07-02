import fetch from 'isomorphic-fetch';

export const API_URL = 'http://localhost:3000/api';

export default async (endpoint, method = 'get', body) => {

    const headers = {
        'content-type': 'application/json'
    };

    const token = localStorage.getItem('token');
    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }

    const options = {
        headers,
        method,
        body: JSON.stringify(body),
    }

    return fetch(`${API_URL}/${endpoint}`, options)
        .then(response => response.json().then(json => ({json, response})))
        .then(({json, response}) => {
            if (!response.ok) {
                return Promise.reject(json);
            }

            return json;
        })
        .then(
            response => response,
            error => error
        );
}

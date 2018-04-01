import {
    callingAPI,
    fetchCarsFailure,
    fetchCarsSuccess,
    finishedCallingAPI,
    loginFailure,
    loginSuccess,
    postRssFailure,
    postRssSuccess
} from '../redux/modules/actions'
import Cookies from 'universal-cookie';

export const getCars = () => {
    return (dispatch) => {

        dispatch(callingAPI());

        // setTimeout(() => {
        //     dispatch(fetchCarsSuccess(stateData.cars));
        // }, 1000);


        fetch('/api/cars', {credentials: 'same-origin'})
            .then(res => {
                return res.json();
            })
            .then(response => {
                dispatch(finishedCallingAPI());
                dispatch(fetchCarsSuccess(response));
            })
            .catch(error => {
                dispatch(finishedCallingAPI());
                dispatch(fetchCarsFailure(error));
            })
    }
}

export const postRss = (rss) => {
    return (dispatch) => {

        dispatch(callingAPI());

        // if (rss) {
        //     setTimeout(() => {
        //         dispatch(postRssSuccess());
        //     }, 3000);
        // } else {
        //     setTimeout(() => {
        //         dispatch(postRssSuccess());
        //     }, 3000);
        // }

        const request = {
            method: 'POST',
            body: rss,
            credentials: 'same-origin',
        }

        fetch("/api/consumeRSS", request)
            .then(res => {
                dispatch(finishedCallingAPI());
                if (res.status === 201) {
                    dispatch(postRssSuccess());
                }
            })
            .catch(error => {
                dispatch(finishedCallingAPI());
                dispatch(postRssFailure(error));
            })
    }
}

export const authenticate = (username, password) => {
    return (dispatch) => {

        dispatch(callingAPI());

        const headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        const cookies = new Cookies();
        const csrf = cookies.get('XSRF-TOKEN');
        const postBody = "_csrf=" + csrf + "&username=" + username + "&password=" + password;

        const request = {
            headers: headers,
            method: 'POST',
            body: postBody,
            credentials: 'same-origin',
        }

        fetch('/login', request)
            .then(response => {
                dispatch(finishedCallingAPI());

                if (response.url.includes("/?error")) {
                    dispatch(loginFailure(username));
                } else {
                    dispatch(loginSuccess(username));
                    dispatch(getCars());
                }
            })
            .catch(error => {
                dispatch(finishedCallingAPI());
                dispatch(loginFailure(username));
            })
    }
}

/*
FOR REFERENCE ONLY

How to make basic authentication request:

import base64 from 'base-64'

let url = '/api/cars';
let username = 'username';
let password = 'password';

let headers = new Headers();
headers.append('Authorization', 'Basic ' + base64.encode(username + ":" + password));

fetch(url, {method:'GET', headers: headers})


 */
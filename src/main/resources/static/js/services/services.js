import {
    callingAPI,
    fetchCarsFailure,
    fetchCarsSuccess,
    finishedCallingAPI,
    loginFailure,
    loginSuccess,
    postRssFailure,
    postRssSuccess,
    logoutAction
} from '../redux/modules/actions'
import Cookies from 'universal-cookie';
import stateData from '../../data/cars.json'

export const getCars = () => {
    return (dispatch) => {

        dispatch(callingAPI());

        // setTimeout(() => {
        //     dispatch(finishedCallingAPI());
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
        //     }, 1000);
        // } else {
        //     setTimeout(() => {
        //         dispatch(postRssSuccess());
        //     }, 1000);
        // }

        const cookies = new Cookies();
        const csrf = cookies.get('XSRF-TOKEN');

        const headers = new Headers();
        headers.append('X-XSRF-TOKEN', csrf);

        const request = {
            headers: headers,
            method: 'POST',
            body: rss,
            credentials: 'same-origin'
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

        // setTimeout(() => {
        //     dispatch(finishedCallingAPI());
        //
        //     if (username != 'andpel') {
        //         dispatch(loginFailure(username));
        //     } else {
        //         dispatch(loginSuccess(username));
        //         dispatch(getCars());
        //     }
        //
        // }, 1000);

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

                if (response.url.includes("/?error")) {
                    dispatch(finishedCallingAPI());
                    dispatch(loginFailure(username));

                } else {

                    fetch('/api/roles', {credentials: 'same-origin'})
                        .then(res => {
                            return res.json();
                        })
                        .then(response => {
                            const role = response[0].authority;
                            dispatch(finishedCallingAPI());
                            dispatch(loginSuccess(username, role));
                            dispatch(getCars());
                        })
                }
            })
            .catch(error => {
                dispatch(finishedCallingAPI());
                dispatch(loginFailure(username));
            })
    }
}

export const logout = () => {
    return (dispatch) => {

        const headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');

        const cookies = new Cookies();
        const csrf = cookies.get('XSRF-TOKEN');
        const postBody = "_csrf=" + csrf;

        const request = {
            headers: headers,
            method: 'POST',
            body: postBody,
            credentials: 'same-origin',
        }

        fetch('/logout', request)
            .then(response => {
               dispatch(logoutAction());
            })
            .catch(error => {
                dispatch(logoutAction());
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
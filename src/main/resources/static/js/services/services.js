import {callingAPI, fetchCarsSuccess, fetchCarsFailure, postRssSuccess} from '../redux/modules/actions'
import stateData from '../../data/cars.json'

export const getCars = () => {
    return (dispatch) => {

        dispatch(callingAPI());

        setTimeout(() => {
            dispatch(fetchCarsSuccess(stateData.cars));
        }, 3000);


        // fetch("/api/cars")
        //     .then(res => {
        //         return res.json();
        //     })
        //     .catch(error => {
        //         dispatch(fetchCarsFailure(error))
        //     })
        //     .then(response => {
        //         dispatch(fetchCarsSuccess(response))
        //     })

    }
}

export const postRss = (rss) => {
    return (dispatch) => {

        dispatch(callingAPI());

        if (rss) {
            setTimeout(() => {
                dispatch(postRssSuccess());
            }, 3000);
        } else {
            setTimeout(() => {
                dispatch(postRssSuccess());
            }, 3000);
        }
    }
}
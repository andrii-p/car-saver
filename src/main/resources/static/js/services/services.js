import {callingAPI, fetchCarsSuccess, fetchCarsFailure} from '../redux/modules/actions'
import stateData from '../../data/cars.json'

export function getCars() {
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
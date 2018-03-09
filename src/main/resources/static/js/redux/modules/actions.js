import C from "./constants";

export const callingAPI = () =>
    ({
        type: C.CALLING_API
    })

export const fetchCarsSuccess = (cars) =>
    ({
        type: C.FETCH_CARS_SUCCESS,
        payload: cars
    })

export const fetchCarsFailure = (error) =>
    ({
        type: C.FETCH_CARS_FAILURE,
        payload: error
    })

export const postRssSuccess = () =>
    ({
        type: C.POST_RSS_SUCCESS
    })

export const postRssFailure= (error) =>
    ({
        type: C.POST_RSS_SUCCESS,
        payload: error
    })
import C from "./constants";

export const callingAPI = () =>
    ({
        type: C.CALLING_API
    })

export const finishedCallingAPI = () =>
    ({
        type: C.FINISHED_CALLING_API
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

export const postRssFailure = (error) =>
    ({
        type: C.POST_RSS_FAILURE,
        payload: error
    })

export const loginSuccess = (username) =>
    ({
        type: C.LOGIN_SUCCESS,
        username
    })

export const loginFailure = (username) =>
    ({
        type: C.LOGIN_FAILURE,
        username
    })
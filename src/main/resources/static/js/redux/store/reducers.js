import C from '../modules/constants';

const initialState = {
    isCallingAPI: false,
    error: null,
    cars: []
};

export const carsReducer = (state = initialState, action) => {
    switch (action.type) {
        case C.CALLING_API :
            return {
                ...state,
                isCallingAPI: true
            };
        case C.FETCH_CARS_SUCCESS :
            return {
                ...state,
                isCallingAPI: false,
                cars: action.payload
            };
        case C.FETCH_CARS_FAILURE :
            return {
                ...state,
                isCallingAPI: false,
                error: action.payload
            };
        default:
            return state;
    }
}


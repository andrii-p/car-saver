import C from '../modules/constants';

const carsInitialState = {
    data: [],
    error: null

}

export const carsReducer = (state = carsInitialState, action) => {
    switch (action.type) {
        case C.FETCH_CARS_SUCCESS :
            return {
                ...state,
                data: action.payload
            };
        case C.FETCH_CARS_FAILURE :
            return {
                ...state,
                error: action.payload
            };
        case C.POST_RSS_SUCCESS :
            return {
                ...state
            };
        case C.POST_RSS_FAILURE :
            return {
                ...state,
                error: action.payload
            };
        default:
            return state;
    }
}

const userInitialState = {
    isAuthenticated: false,
    username: null,
    role: null
}

export const userReducer = (state = userInitialState, action) => {
    switch (action.type) {
        case C.LOGIN_SUCCESS :
            return {
                ...state,
                isAuthenticated: true,
                username: action.username,
                role: action.role
            };
        case C.LOGIN_FAILURE :
            return {
                ...state,
                isAuthenticated: false,
                username: action.username
            };
        case C.LOGOUT :
            return {
                ...state,
                isAuthenticated: false,
                username: null,
                role: null
            };
        default:
            return state;
    }
}

export const callingAPIReducer = (state = false, action) => {
    switch (action.type) {
        case C.CALLING_API :
            return true;
        case C.FINISHED_CALLING_API :
            return false;
        default:
            return state;
    }
}


import {combineReducers} from 'redux';
import {callingAPIReducer, carsReducer, userReducer} from "./reducers";

const rootReducer = combineReducers({
    carsReducer,
    user: userReducer,
    isCallingAPI: callingAPIReducer
});

export default rootReducer;
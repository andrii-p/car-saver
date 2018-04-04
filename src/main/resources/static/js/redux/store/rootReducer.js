import {combineReducers} from 'redux';
import {callingAPIReducer, carsReducer, userReducer} from "./reducers";

const rootReducer = combineReducers({
    cars: carsReducer,
    user: userReducer,
    isCallingAPI: callingAPIReducer
});

export default rootReducer;
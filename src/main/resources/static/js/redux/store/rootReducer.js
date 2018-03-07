import {combineReducers} from 'redux';
import {carsReducer} from "./reducers";

const rootReducer = combineReducers({
    carsReducer
});

export default rootReducer;
import {createStore, applyMiddleware} from 'redux';
import rootReducer from './rootReducer';
import thunk from 'redux-thunk';

let console = window.console;

const logger = store => next => action => {
    let result;
    console.groupCollapsed("dispatching", action.type);
    console.log('prev state', store.getState());
    console.log('action', action);
    result = next(action);
    console.log('next state', store.getState());
    console.groupEnd();
    return result;
}

const saver = store => next => action => {
    let result = next(action);
    localStorage['redux-store'] = JSON.stringify(store.getState());
    return result;
}

const initialState = {
    isCallingAPI: false,
    error: null,
    cars: []
};

const configureStore = () =>
    createStore(
        rootReducer,
        initialState,
        applyMiddleware(thunk, logger, saver)
    )

export default configureStore
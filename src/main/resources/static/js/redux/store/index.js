import { createStore, combineReducers, applyMiddleware } from 'redux'
import { carsReducer } from './reducers'
import stateData from '../../data/cars.json'

let console = window.console

const logger = store => next => action => {
    let result
    console.groupCollapsed("dispatching", action.type)
    console.log('prev state', store.getState())
    console.log('action', action)
    result = next(action)
    console.log('next state', store.getState())
    console.groupEnd()
    return result
}

const saver = store => next => action => {
    let result = next(action)
    localStorage['redux-store'] = JSON.stringify(store.getState())
    return result
}

// const storeFactory = (initialState=stateData) =>
//     applyMiddleware(logger, saver)(createStore)(
//         combineReducers({carsReducer}),
//         (localStorage['redux-store']) ?
//             JSON.parse(localStorage['redux-store']) :
//             initialState
//     )

const initialState = localStorage['redux-store'] || stateData

// const initialState = {
//     isFetching: false,
//     cars: []
// }

const store = createStore(
    combineReducers({...carsReducer}),
    initialState,
    applyMiddleware(logger, saver)
)

console.log({carsReducer})
console.log({...carsReducer})

export default store
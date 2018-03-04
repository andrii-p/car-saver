import C from '../constants'

export const carsReducer = (state = [], action={ type: null }) => {
    switch (action.type) {
        case C.TEST_ACTION :
            console.log(action.text)
            return [
                state
            ]
        default:
            return state
    }
}
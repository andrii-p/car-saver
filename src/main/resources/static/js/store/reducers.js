export const carsReducer = (state = [], action={ type: null }) => {
    switch (action.type) {
        case "TEST_ACTION" :
            console.log(action.text)
            return [
                state
            ]
        default:
            return state
    }
}
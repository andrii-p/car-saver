import {connect} from 'react-redux'
import CarsTable from './ui/CarsTable'
import {testAction} from "../actions";

export const CarsContainer = connect(
    state =>
        ({
            cars: state.cars
        }),
    dispatch =>
        ({
            onTest() {
                dispatch(testAction())
            }
        })
)(CarsTable)


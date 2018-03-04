import {connect} from 'react-redux'
import CarsTable from './ui/CarsTable'
import {callingAPI} from "../redux/modules/actions";

export const CarsContainer = connect(
    state =>
        ({
            cars: state.cars,
            isCallingAPI: state.isCallingAPI
        }),
    dispatch =>
        ({
            onTest() {
                dispatch(callingAPI())
            }
        })
)(CarsTable)


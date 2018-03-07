import {connect} from 'react-redux'
import CarsTable from './ui/CarsTable'
import CarDetails from './ui/CarDetails'
import {findById} from "../lib/array-helpers";

export const CarsContainer = connect(
    state =>
        ({
            cars: state.carsReducer.cars,
            isCallingAPI: state.carsReducer.isCallingAPI
        })
)(CarsTable)

export const CarContainer = connect(
    (state, props) =>
        ({
            car: findById(state.carsReducer.cars, parseInt(props.match.params.id))
        })
)(CarDetails)


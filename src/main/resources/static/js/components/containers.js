import {connect} from 'react-redux'
import CarsTable from './ui/CarsTable'
import CarDetails from './ui/CarDetails'
import PostForm from './ui/PostForm'
import {findById} from "../lib/array-helpers";
import { postRss } from "../services/services";

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

export const PostFormContainer = connect(
    state =>
        ({
            isCallingAPI: state.carsReducer.isCallingAPI
        }),
    dispatch =>
        ({
            postRss: rss => dispatch(postRss(rss))
        })
)(PostForm)


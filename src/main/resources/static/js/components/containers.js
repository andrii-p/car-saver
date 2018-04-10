import {connect} from 'react-redux'
import Home from './ui/Home'
import Menu from './ui/Menu'
import CarDetails from './ui/CarDetails'
import PostForm from './ui/PostForm'
import {findById} from "../lib/array-helpers";
import {authenticate, postRss, logout} from "../services/services";

export const MenuContainer = connect(
    state =>
        ({
            user: state.user
        }),
    dispatch =>
        ({
            logout: () => dispatch(logout())
        })
)(Menu)

export const HomeContainer = connect(
    state =>
        ({
            cars: state.cars,
            isCallingAPI: state.isCallingAPI,
            user: state.user
        }),
    dispatch =>
        ({
            authenticate: (username, password) => dispatch(authenticate(username, password))
        })
)(Home)

export const CarContainer = connect(
    (state, props) =>
        ({
            car: findById(state.cars.data, parseInt(props.match.params.id))
        })
)(CarDetails)

export const PostFormContainer = connect(
    state =>
        ({
            isCallingAPI: state.isCallingAPI,
            user: state.user
        }),
    dispatch =>
        ({
            postRss: rss => dispatch(postRss(rss))
        })
)(PostForm)


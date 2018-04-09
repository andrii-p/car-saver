import {Component} from 'react'
import CarsTable from './CarsTable'
import PropTypes from 'prop-types'
import Spinner from './Spinner'
import '../../../stylesheets/input-form-common.scss'

class Home extends Component {

    constructor(props) {
        super(props);
        this.authenticate = this.authenticate.bind(this);
    }

    authenticate(e) {
        e.preventDefault();
        const {_username, _password} = this.refs;
        this.props.authenticate(_username.value, _password.value);
    }

    render() {
        const {cars, isCallingAPI, user} = this.props;

        const loginForm = (
            <div className="input-form-common">
                <form onSubmit={this.authenticate}>
                    <div className="form-group">
                        <label for="username">Username</label>
                        <input type="text" className='form-control' id="username" ref="_username" name="username"/>
                    </div>

                    <div className="form-group">
                        <label for="password">Password</label>
                        <input type="password" className='form-control' id="password" ref="_password" name="password"/>
                    </div>

                    {user.username != null &&
                    <div className="alert alert-danger form-group">
                        Invalid username or password
                    </div>
                    }

                    <button type="submit" className="btn center-block">Log in</button>
                </form>
            </div>
        )

        return (
            isCallingAPI ?
                <Spinner/>
                :
                user.isAuthenticated ?
                    <CarsTable cars={cars.data}/>
                    :
                    loginForm
        )
    }
}

Home.propTypes = {
    cars: PropTypes.object.isRequired,
    isCallingAPI: PropTypes.bool.isRequired,
    user: PropTypes.object.isRequired
}

Home.defaultProps = {
    cars: {},
    isCallingAPI: false,
    user: {}
}

export default Home
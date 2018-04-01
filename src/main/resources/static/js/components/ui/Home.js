import {Component} from 'react'
import CarsTable from './CarsTable'
import Spinner from './Spinner'

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

        const form = (
            <div className="container">
                <form class="form-inline" onSubmit={this.authenticate}>
                    <label for="username">Username</label>
                    <input type="text" id="username" ref="_username" name="username"/>
                    <label for="password">Password</label>
                    <input type="password" id="password" ref="_password" name="password"/>
                    <div class="form-actions">
                        <button type="submit" class="btn">Log in</button>
                    </div>
                </form>
            </div>
        )

        return (
            isCallingAPI ?
                <Spinner/>
                :
                user.isAuthenticated ?
                    <CarsTable cars={cars}/>
                    :
                    form
        )
    }
}

export default Home
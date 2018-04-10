import FontAwesomeIcon from '@fortawesome/react-fontawesome'
import faHome from '@fortawesome/fontawesome-free-solid/faHome'
import PropTypes from 'prop-types'
import {NavLink} from 'react-router-dom'
import '../../../stylesheets/menu.scss'

const selectedStyle = {
    backgroundColor: "#555"
}

const Menu = ({user, logout}) =>
    <div>
        {user.isAuthenticated ?
            <nav className="main-menu">
                <NavLink to="/"><FontAwesomeIcon icon={faHome}/></NavLink>
                <NavLink to="/stats" activeStyle={selectedStyle}>Statistics</NavLink>
                <NavLink to="/postRss" activeStyle={selectedStyle}>Post RSS</NavLink>
                <NavLink to="/" onClick={logout}>Logout</NavLink>
                <span id="username">Welcome {user.username}</span>
            </nav>

            :

            <nav className="main-menu">
                <span id="username">Welcome</span>
            </nav>
        }
    </div>


Menu.propTypes = {
    user: PropTypes.object.isRequired
}

export default Menu
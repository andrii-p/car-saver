import FontAwesomeIcon from '@fortawesome/react-fontawesome'
import faHome from '@fortawesome/fontawesome-free-solid/faHome'
import { NavLink } from 'react-router-dom'
import '../../../stylesheets/menu.scss'

const selectedStyle = {
    backgroundColor: "#555"
}

const Menu = () =>
    <nav className="main-menu">
        <NavLink to="/"><FontAwesomeIcon icon={faHome}/></NavLink>
        <NavLink to="/stats" activeStyle={selectedStyle}>Statistics</NavLink>
        <NavLink to="/cars" activeStyle={selectedStyle}>Test Cars</NavLink>
    </nav>

export default Menu
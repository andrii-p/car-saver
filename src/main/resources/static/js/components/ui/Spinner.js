import FontAwesomeIcon from '@fortawesome/react-fontawesome'
import faSpinner from '@fortawesome/fontawesome-free-solid/faSpinner'
import '../../../stylesheets/spinner.scss'

const Spinner = () =>
    <div className="spinner">
        <FontAwesomeIcon icon={faSpinner} pulse size="5x"/>
    </div>

export default Spinner
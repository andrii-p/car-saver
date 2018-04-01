import PropTypes from 'prop-types'
import {withRouter} from 'react-router-dom'

const CarRow = ({car = {}, history}) =>
    <tr>
        <td>{car.adName}</td>
        <td>{car.price}</td>
        <td>{car.location}</td>
        <td>{car.odometer}</td>
        <td>{car.condition}</td>
        <td>
            <button className="btn btn-success"
                    onClick={() => history.push(`/cars/${car.id}`)}>Details
            </button>
        </td>

    </tr>

CarRow.propTypes = {
    car: PropTypes.object.isRequired
}

export default withRouter(CarRow)
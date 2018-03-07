import {withRouter} from 'react-router-dom'

const CarRow = ({car, history}) =>
    <tr>
        <td>{car.adName}</td>
        <td>{car.price}</td>
        <td>{car.location}</td>
        <td>{car.odometer}</td>
        <td>{car.condition}</td>
        <td>
            <button className="btn btn-success"
                    onClick={() => history.push(`/${car.id}`)}>Details
            </button>
        </td>

    </tr>

export default withRouter(CarRow)